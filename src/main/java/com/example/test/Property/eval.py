import sys

try:
    print(eval(sys.argv[1]))
except Exception as e:
    sys.exit(1)
