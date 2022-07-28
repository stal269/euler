from math import floor, sqrt

class R:
    def __init__(self, n, d):
        self.n = n
        self.d = d

    def val(self):
        return self.n / self.d

    def inverse(self):
        return R(self.d, self.n)

    def __str__(self):
        return str(self.n) + " / " + str(self.d)

def gcd(a, b):
    return a if (b == 0) else gcd(b, a % b)

def add(integer, rational):
    return R(integer * rational.d + rational.n, rational.d)

def getBaseNumber(num):
    return floor(sqrt(num))

def getPeriod(d):
    base = getBaseNumber(d)
    period = []
    dupSet = set()
    next = R(1, sqrt(d) - base)

    while (R(next.d, next.n).val()) not in dupSet:
        dupSet.add(R(next.d, next.n).val())
        secondN = ((next.d - sqrt(d)) * -1)
        secondD = round((sqrt(d) + secondN) *  next.d)
        myGcd = gcd(next.n, secondD)
        second = R(secondN * (next.n / myGcd), secondD / myGcd)
        period.append((second.n + base) // second.d)
        remainder = (second.n + base) % second.d
        third = R(sqrt(d) - (base - remainder), second.d)
        next = R(third.d, third.n)

    return period