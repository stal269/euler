from math import floor, sqrt
from utils import R, getPeriod, add

dSpace = [d for d in range(2,1001) if floor(sqrt(d)) != sqrt(d)]

maxX = 1
resultD = 1

def isSolution(d, rational):
    x = rational.n
    y = rational.d 

    return x * x - d * y * y == 1

def getCoeff(currI, period):
    coeffIdx = currI % len(period)

    return period[coeffIdx]

def getSolutionFraction(d, period):
    coeffIdx = 0
    base = int(floor(sqrt(d)))
    coeff = getCoeff(coeffIdx, period)
    preFrac = R(1, coeff)
    frac = add(base, preFrac)

    while (not isSolution(d, frac)):
        coeffIdx += 1
        coeff = getCoeff(coeffIdx, period)
        nextNom = preFrac.d
        nextDenom = int(int(preFrac.d) * int(coeff) + int(preFrac.n))
        preFrac = R(nextNom, nextDenom)
        frac = add(base, preFrac)

    return frac

for D in dSpace:
    period = getPeriod(D)
    fraction = getSolutionFraction(D, period)
    x = fraction.n

    if x > maxX:
        maxX = x
        resultD = D

print("resultD: ", resultD)
