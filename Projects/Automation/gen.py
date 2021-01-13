#! /usr/bin/env python3

"""
This script partially parses CSPICE.java and generates
the list of function declarations it contains.
"""

import sys
from antlr4 import FileStream, CommonTokenStream
from DeclarationsLexer import DeclarationsLexer
from DeclarationsParser import DeclarationsParser


def main(argv):
    """
    CLI arg is the path to CSPICE.java.
    """

    input_stream = FileStream(argv[1])
    lexer = DeclarationsLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = DeclarationsParser(stream)
    functions = parser.cspice().result
    print("Test output for first function axisar:")
    print(functions[0].name)
    print(functions[0].return_type)
    print(functions[0].args[0])
    print(functions[0].args[1])
    print(functions[0].throws)


if __name__ == '__main__':
    main(sys.argv)
