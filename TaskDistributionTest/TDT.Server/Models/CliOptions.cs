using CommandLine;
using System;
using System.Collections.Generic;
using System.Text;

namespace TDT.Server.Models
{
    public class CliOptions
    {
        [Option('p', "Port", Required = true, HelpText = "Set the server listening port.")]
        public int ServerPort { get; set; }

    }
}
