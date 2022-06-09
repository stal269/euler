from math import sqrt, floor
from utils import R, gcd

def getBaseNumber(num):
    return floor(sqrt(num))

def isOddPeriod(num):
    base = getBaseNumber(num)
    if (base == sqrt(num)): return False
    period = []
    dupSet = set()
    next = R(1, sqrt(num) - base)

    while (R(next.d, next.n).val()) not in dupSet:
        dupSet.add(R(next.d, next.n).val())
        secondN = ((next.d - sqrt(num)) * -1)
        secondD = round((sqrt(num) + secondN) *  next.d)
        myGcd = gcd(next.n, secondD)
        second = R(secondN * (next.n / myGcd), secondD / myGcd)
        period.append((second.n + base) // second.d)
        remainder = (second.n + base) % second.d
        third = R(sqrt(num) - (base - remainder), second.d)
        next = R(third.d, third.n)
    return len(period) % 2 == 1

print (len([i for i in range(2, 10001) if isOddPeriod(i)]))