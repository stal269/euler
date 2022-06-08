from math import sqrt, floor

class R:
    def __init__(self, n, d):
        self.n = n
        self.d = d

    def __str__(self):
        return str(self.n) + " / " + str(self.d)

def getBaseNumber(num):
    return floor(sqrt(num))

def gcd(a, b):
    if(b == 0):
        return a
    else:
        return gcd(b, a % b)

def rValue(frac):
    return frac.n / frac.d

def isOddPeriod(num):
    base = getBaseNumber(num)
    if (base == sqrt(num)): return False
    periods = []
    dupSet = set()
    next = R(1, sqrt(num) - base)

    while rValue(R(next.d, next.n)) not in dupSet:
        dupSet.add(rValue(R(next.d, next.n)))
        secondN = ((next.d - sqrt(num)) * -1)
        nextN = next.n
        secondD = round((sqrt(num) + secondN) *  next.d)
        myGcd = gcd(nextN, secondD)
        second = R(secondN * (next.n / myGcd), secondD / myGcd)
        periods.append((second.n + base) // second.d)
        remainder = (second.n + base) % second.d
        third = R(sqrt(num) - (base - remainder), second.d)
        next = R(third.d, third.n)
    return len(periods) % 2 == 1

count = 0

for i in range(2,10001):
    if isOddPeriod(i):
        count += 1

print (count)

