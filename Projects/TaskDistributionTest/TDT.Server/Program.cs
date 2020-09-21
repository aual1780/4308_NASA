//https://github.com/grpc/grpc/blob/master/examples/csharp/Helloworld/GreeterServer/Program.cs

using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;
using CommandLine;
using Grpc.Core;
using MathCalc.OneArg;
using TDT;
using TDT.Server.Models;
using TIPC.Core.Tools.Extensions;

namespace GreeterServer
{
    class MathCalcOneArgImpl : MathCalc.OneArg.MathCalc.MathCalcBase
    {
        static Dictionary<string, Func<double, double>> _delegateLookup = new Dictionary<string, Func<double, double>>();

        // Server side handler of the SayHello RPC
        public override Task<MathCalcReply> DoMath(MathCalcRequest request, ServerCallContext context)
        {
            var funcName = request.CalcName;
            Func<double, double> funcDelegate = null;
            if (!_delegateLookup.TryGetValue(funcName, out funcDelegate))
            {
                var flags = BindingFlags.Public | BindingFlags.Static;
                var func = typeof(MathOperations).GetMethod(funcName, flags);
                funcDelegate = func.CreateDelegate<Func<double, double>>();
                _delegateLookup[funcName] = funcDelegate;
            }

            var reply = new MathCalcReply()
            { 
                BatchID = request.BatchID
            };
            foreach (var arg in request.Args)
            {
                var answer = funcDelegate(arg);
                reply.Responses.Add(answer);
            }

            return Task.FromResult(reply);
        }
    }

    class Program
    {
        public static void Main(string[] args)
        {
            //[-p]: server port number

            CliOptions options = null;
            var parseResult = Parser.Default.ParseArguments<CliOptions>(args);
            _ = parseResult.WithParsed(o =>
            {
                options = o;
            });
            if (options is null)
            {
                throw new InvalidOperationException("Invalid arguments");
            }

            Server server = new Server
            {
                Services = { MathCalc.OneArg.MathCalc.BindService(new MathCalcOneArgImpl()) },
                Ports = { new ServerPort("localhost", options.ServerPort, ServerCredentials.Insecure) }
            };
            server.Start();

            Console.WriteLine("MathCalc server listening on port " + options.ServerPort);
            Console.WriteLine("Press any key to stop the server...");
            Console.ReadKey();

            server.ShutdownAsync().Wait();
        }
    }
}