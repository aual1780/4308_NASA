using Grpc.Core;
using MathCalc.OneArg;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace TDT.Client
{
    public class DistributedTaskState
    {
        public string CalcName { get; }
        public int WorkerCount { get; }
        public int BatchSize { get; }
        public Func<int, double> ArgFactory { get; }
        public EventHandler<MathCalcReply> ResponseCallback { get; }
        public AutoResetEvent RequestWaitHandle { get; }
        public AutoResetEvent ResponseWaitHandle { get; }
        public AsyncUnaryCall<MathCalcReply>[] CurrentTaskBatch { get; }
        public TaskCompletionSource<object> CompletionSource { get; } = new TaskCompletionSource<object>();

        public DistributedTaskState(string CalcName, int WorkerCount, int BatchSize, Func<int, double> ArgFactory, EventHandler<MathCalcReply> ResponseCallback)
        {
            this.CalcName = CalcName;
            this.WorkerCount = WorkerCount;
            this.BatchSize = BatchSize;
            this.ArgFactory = ArgFactory;
            this.ResponseCallback = ResponseCallback;
            CurrentTaskBatch = new AsyncUnaryCall<MathCalcReply>[WorkerCount];
            RequestWaitHandle = new AutoResetEvent(true);
            ResponseWaitHandle = new AutoResetEvent(false);

        }

    }
}
