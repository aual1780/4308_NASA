//https://github.com/grpc/grpc/blob/master/examples/csharp/Helloworld/GreeterClient/Program.cs

using System;
using Grpc.Core;
using TDT;
using MathCalc.OneArg;
using System.Threading.Tasks;
using Google.Protobuf;
using TIPC.Core.Tools.Extensions;
using System.IO;
using System.Diagnostics;
using System.Reflection;
using System.Threading;

namespace TDT.Client
{
    class Program
    {
        static int reponseCumCount = 0;

        static async Task<int> Main(string[] args)
        {
            var serverPath = new FileInfo("../../../../TDT.Server/bin/Debug/netcoreapp3.1/TDT.Server.exe");
            var initPort = 30672;
            var workerCount = 6;
            string mathFunc = nameof(MathOperations.MathTransform2);
            var maxBatchSize = 100000;
            int batchSize = 1000000;
            Func<int, double> argFactory = (x) => x;
            using var engine = new WorkerPool(serverPath, initPort, workerCount, maxBatchSize);
            await Task.Delay(500);
            var stopWatch = new Stopwatch();
            string log1 = null;
            string log2 = null;
            string log3 = null;
            string log4 = null;
            string log5 = null;
            string log6 = null;

            //throw away init code
            await engine.PerformDistributedTask(mathFunc, 10, argFactory, MathCalcCallback);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            reponseCumCount = 0;
            stopWatch.Restart();
            await engine.PerformDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
            stopWatch.Stop();
            log3 = "Distributed call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;

            reponseCumCount = 0;
            stopWatch.Restart();
            await engine.PerformDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
            stopWatch.Stop();
            log4 = "Distributed call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;

            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            reponseCumCount = 0;
            stopWatch.Restart();
            await engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
            stopWatch.Stop();
            log1 = "Distributed threaded call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;

            reponseCumCount = 0;
            stopWatch.Restart();
            await engine.PerformThreadedDistributedTask(mathFunc, batchSize, argFactory, MathCalcCallback);
            stopWatch.Stop();
            log2 = "Distributed threaded call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;

            /////////////////////////////////////////////////////////////////////////////////////////////////////
            reponseCumCount = 0;
            stopWatch.Restart();
            PerformNondistributedTask(mathFunc, maxBatchSize, batchSize, argFactory, MathCalcCallback);
            stopWatch.Stop();
            log5 = "Nondistributed call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;

            //reponseCumCount = 0;
            //stopWatch.Restart();
            //PerformNondistributedTask(mathFunc, maxBatchSize, batchSize, argFactory, MathCalcCallback);
            //stopWatch.Stop();
            //log6 = "Nondistributed call elapsed time (millis) " + stopWatch.ElapsedMilliseconds;


            Console.WriteLine(log1);
            Console.WriteLine(log2);
            Console.WriteLine(log3);
            Console.WriteLine(log4);
            Console.WriteLine(log5);
            Console.WriteLine(log6);

            Console.WriteLine("Press any key to exit...");
            Console.ReadKey();

            return 0;
        }



        static void MathCalcCallback(object Sender, MathCalcReply Reply)
        {
            reponseCumCount += Reply.Responses.Count;
            Console.WriteLine($"Response group returned: {Reply.BatchID}");
            Console.WriteLine($"Cumulative responses returned: {reponseCumCount}");
            Thread.Sleep(20);
        }


        static void PerformNondistributedTask(string CalcName, int MaxBatchSize, int BatchSize, Func<int, double> ArgFactory, EventHandler<MathCalcReply> ResponseCallback)
        {
            var funcName = CalcName;
            var flags = BindingFlags.Public | BindingFlags.Static;
            var func = typeof(MathOperations).GetMethod(funcName, flags);
            var funcDelegate = func.CreateDelegate<Func<double, double>>();

            var reply = new MathCalcReply()
            {
                BatchID = 1
            };
            for (int i = 0; i < BatchSize; ++i)
            {
                var arg = ArgFactory(i);
                var result = funcDelegate(arg);
                reply.Responses.Add(result);
                if (i % MaxBatchSize == MaxBatchSize - 1)
                {
                    ResponseCallback?.Invoke(null, reply);
                    reply = new MathCalcReply()
                    {
                        BatchID = 1
                    };
                }
            }
        }
    }
}
