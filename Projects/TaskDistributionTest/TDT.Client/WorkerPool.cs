using Grpc.Core;
using MathCalc.OneArg;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using TIPC.Core.Tools.Threading;

namespace TDT.Client
{
    public class WorkerPool : IDisposable
    {
        Process[] _processCollection { get; }
        Channel[] _channelCollection { get; }
        MathCalc.OneArg.MathCalc.MathCalcClient[] _mathClients { get; }
        int _maxBatchSize { get; }

        public WorkerPool(FileInfo ServerPath, int StartPort, int WorkerCount, int MaxBatchSize)
        {
            var currentPort = StartPort;
            _maxBatchSize = MaxBatchSize;
            _channelCollection = new Channel[WorkerCount];
            _processCollection = new Process[WorkerCount];
            _mathClients = new MathCalc.OneArg.MathCalc.MathCalcClient[WorkerCount];
            for (int i = 0; i < WorkerCount; ++i)
            {
                var port = currentPort++;
                var args = $"-p {port}";
                var proc = Process.Start(ServerPath.FullName, args);
                _processCollection[i] = proc;
                _channelCollection[i] = new Channel($"127.0.0.1:{port}", ChannelCredentials.Insecure);
                _mathClients[i] = new MathCalc.OneArg.MathCalc.MathCalcClient(_channelCollection[i]);
            }
        }


        public async Task PerformThreadedDistributedTask(
            string CalcName, 
            int BatchSize, 
            Func<int, double> ArgFactory, 
            EventHandler<MathCalcReply> ResponseCallback)
        {
            var state = new DistributedTaskState(CalcName, _channelCollection.Length, BatchSize, ArgFactory, ResponseCallback);

            var t1 = new CancelThread<DistributedTaskState>(Thread1Worker);
            var t2 = new CancelThread<DistributedTaskState>(Thread2Worker);
            t1.Start(state);
            t2.Start(state);

            _ = await state.CompletionSource.Task;
        }


        public void Thread1Worker(DistributedTaskState state, CancellationToken Token)
        {
            int batchID = 0;
            int currentBatchSize = state.BatchSize;
            int currentIdx = 0;

            //continue working until the entire batch is processed
            while (currentIdx < currentBatchSize)
            {
                state.RequestWaitHandle.WaitOne();

                //send batch request to each worker
                //send all batch requests to the workers at once
                //the batches are sent out in waves
                //this allows the response processor to guarantee in-order evaluation

                for (int i = 0; i < _channelCollection.Length && currentIdx < currentBatchSize; ++i)
                {
                    //make a new batch request for this worker
                    var rqstSize = 0;
                    var mathRequest = new MathCalcRequest()
                    {
                        BatchID = batchID++,
                        CalcName = state.CalcName
                    };
                    //Calculate the args for this request
                    //this allows the client to provide an arg factory and lazily evaluate arguments
                    //prevents all arguments from being stored in memory at once
                    for (var j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                    {
                        var arg = state.ArgFactory(currentIdx);
                        mathRequest.Args.Add(arg);
                    }
                    //dont send request if the batch is empty
                    if (rqstSize == 0)
                    {
                        continue;
                    }
                    //send request to next worker
                    //add awaitable to stack
                    var requestTask = _mathClients[i].DoMathAsync(mathRequest);
                    state.CurrentTaskBatch[i] = requestTask;
                }
                state.ResponseWaitHandle.Set();
            }
        }

        public async void Thread2Worker(DistributedTaskState state, CancellationToken Token)
        {
            //wait for batch responses
            //separates batch dispatching from result callback processing
            //allows one thread to push out new requests while this thread evaluates responses
            //improves performance with non-trivial callbacks

            var cumResponseCount = 0;
            var batchSize = state.BatchSize;

            while (cumResponseCount < batchSize)
            {
                state.ResponseWaitHandle.WaitOne();

                var resultClone = new MathCalcReply[_channelCollection.Length];

                //wait for all worker responses to return before sending new batches
                //process the receipts, then repeat the process
                //cache responses in 2nd buffer
                for (int i = 0; i < _channelCollection.Length; ++i)
                {
                    var task = state.CurrentTaskBatch[i];
                    if (task == null)
                        continue;
                    var response = await task;
                    resultClone[i] = response;
                    state.CurrentTaskBatch[i] = null;
                }
                //let requester thread send new wave of batches
                state.RequestWaitHandle.Set();
                //process response callbacks while new batch wave is being processed by workers
                for (int i = 0; i < _channelCollection.Length; ++i)
                {
                    var response = resultClone[i];
                    if(response != null)
                    {
                        cumResponseCount += response.Responses.Count;
                        state.ResponseCallback?.Invoke(this, response);
                    }
                }
            }
            state.CompletionSource.SetResult(null);
        }



        public async Task PerformDistributedTask(
            string CalcName, 
            int BatchSize, 
            Func<int, double> ArgFactory, 
            EventHandler<MathCalcReply> ResponseCallback)
        {
            var state = new DistributedTaskState(CalcName, _channelCollection.Length, BatchSize, ArgFactory, ResponseCallback);

            int batchID = 0;
            int currentBatchSize = state.BatchSize;
            int currentIdx = 0;

            //continue working until the entire batch is processed
            while (currentIdx < currentBatchSize)
            {
                for (int i = 0; i < _channelCollection.Length; ++i)
                {
                    state.CurrentTaskBatch[i] = null;
                }

                //send batch cluster to each worker
                //send batches to each cluster at once
                for (int i = 0; i < _channelCollection.Length && currentIdx < currentBatchSize; ++i)
                {
                    //make a new batch request for this worker
                    var rqstSize = 0;
                    var mathRequest = new MathCalcRequest()
                    {
                        BatchID = batchID++,
                        CalcName = state.CalcName
                    };
                    //Calculate the args for this request
                    //this allows the client to provide an arg factory and lazily evaluate arguments
                    //prevents all arguments from being stored in memory at once
                    for (var j = 0; j < _maxBatchSize && currentIdx < currentBatchSize; ++j, ++currentIdx, ++rqstSize)
                    {
                        var arg = state.ArgFactory(currentIdx);
                        mathRequest.Args.Add(arg);
                    }
                    //dont send request if the batch is empty
                    if (rqstSize == 0)
                    {
                        continue;
                    }
                    //send request to next worker
                    //add awaitable to stack
                    var requestTask = _mathClients[i].DoMathAsync(mathRequest);
                    state.CurrentTaskBatch[i] = requestTask;
                }

                //wait for all worker responses to return before sending new batches
                //process the receipts, then repeat the process
                for (int i = 0; i < _channelCollection.Length; ++i)
                {
                    var task = state.CurrentTaskBatch[i];
                    if (task == null)
                        continue;
                    var response = await task;
                    state.ResponseCallback?.Invoke(this, response);
                }
            }
        }



        /// <summary>
        /// Shutdown spawned server processes and clients
        /// </summary>
        public void Dispose()
        {
            foreach (var channel in _channelCollection)
            {
                channel.ShutdownAsync().Wait();
            }
            foreach (var proc in _processCollection)
            {
                proc.Dispose();
            }
        }

    }
}
