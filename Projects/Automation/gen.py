#! /usr/bin/env python3

"""
This script partially parses CSPICE.java and generates
the list of function declarations it contains.
"""

import sys
import os
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

    out = 'out'
    if not os.path.exists(out):
        os.makedirs(out)

    for func in functions:
        try:
            generate_files(func, ['Object.java', 'Command.java'], out)
        except ValueError as _:
            print('not yet working: %s', func.name)


def generate_files(func, templates, out):

    upper_name = func.name.capitalize()
    lower_name = func.name
    
    fields = ''
    defaults = ''
    args = ''
    args_no_types = ''
    assign_args = ''

    for i,arg in enumerate(func.args):

        ty = arg.data_type.base_to_str()
        for _ in range(arg.data_type.array_depth):
            ty = 'ArrayList<%s>' % ty.capitalize()
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
                raise ValueError("i'm not ready for these yet")
        else:
            default = 'new %s()' % ty
        defaults += 'arg%i = %s;\n' % (i, default)

        args += '%s a%i, ' % (ty, i)
        args_no_types += 'a%i, ' % i
        assign_args += 'arg%i = a%i;\n' % (i,i)
    
    args = args[:-2]
    args_no_types = args_no_types[:-2]
            

    for template in templates:
        with open('templates/%s' % template, 'r') as in_file:
            output = in_file.read() \
                .replace('###LOWER_NAME###', lower_name) \
                .replace('###UPPER_NAME###', upper_name) \
                .replace('###FIELDS###', fields) \
                .replace('###DEFAULTS###', defaults) \
                .replace('###ARGS###', args) \
                .replace('###ARGS_NO_TYPES###', args_no_types) \
                .replace('###ASSIGN_ARGS###', assign_args)
            with open('%s/%s%s' % (out, upper_name, template), 'w') as out_file:
                out_file.write(output)



if __name__ == '__main__':
    main(sys.argv)
