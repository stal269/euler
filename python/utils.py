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