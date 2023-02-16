from math import log

# so here are the steps
# 1. read all the pairs
# 2. for each pair map base, exponent -> exponent * log(base)
# 3. you need to remember the index of the pair
# 4. so you have lets say for now pair of the form (x, exponent * log(base))
# 5. how do you find the max pair? in java I would have sort it with comparator using
# some data structure

def getBaseExpPair(strLine):
    cmps = strLine.split(",")

    return int(cmps[1]) * log(int(cmps[0]))

f = open("p099_base_exp.txt", "r")

linesStr = f.read()
lines = linesStr.split("\n")
numbers = list(map(getBaseExpPair, lines))

maxIdx = 0
maxX = numbers[0]

for idx, x in enumerate(numbers):
    if x > maxX:
        maxIdx = idx
        maxX = x

print(maxIdx + 1)
