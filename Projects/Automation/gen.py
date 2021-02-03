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
    print(generate_object_file(functions[0]))


def generate_object_file(func):

    upper_name = func.name.capitalize()
    
    fields = ''
    defaults = ''
    args = ''
    assign_args = ''

    for i,arg in enumerate(func.args):

        ty = arg.data_type.base_to_str()
        for _ in range(arg.data_type.array_depth):
            ty = 'ArrayList<%s>' % ty
        fields += 'public %s arg%i;\n' % (ty, i)

        default = ''
        if arg.data_type.array_depth == 0:
            if ty == 'void':
                raise ValueError('args cant be void, fool')
            elif ty == 'int':
                default = '0'
            elif ty == 'double':
                default = '0.0'
            elif ty == 'boolean':
                default = 'false'
            elif ty == 'String':
                default = '"arg%i"' % i
            else:
                raise NotImplementedError("i'm not ready for these yet")
        else:
            default = 'new %s()' % ty
        defaults += 'arg%i = %s;\n' % (i, default)

        args += 'a%i,' % i
        assign_args += 'arg%i = a%i;\n' % (i,i)
    
    args = args[:-1]
            

    with open('templates/Object.java.temp', 'r') as file:
        return file.read() \
            .replace('###UPPER_NAME###', upper_name) \
            .replace('###FIELDS###', fields) \
            .replace('###DEFAULTS###', defaults) \
            .replace('###ARGS###', args) \
            .replace('###ASSIGN_ARGS###', assign_args)



if __name__ == '__main__':
    main(sys.argv)
