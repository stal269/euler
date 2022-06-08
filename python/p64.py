from math import sqrt, floor

class R:
    def __init__(self, n, d):
        self.n = n
        self.d = d

    def val(self):
        return self.n / self.d

    def __str__(self):
        return str(self.n) + " / " + str(self.d)

def getBaseNumber(num):
    return floor(sqrt(num))

def gcd(a, b):
    return a if (b == 0) else gcd(b, a % b)

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

