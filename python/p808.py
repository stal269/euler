from sympy import sieve
from sympy.ntheory.primetest import isprime
from math import sqrt, floor

primes = [i for i in sieve.primerange(100000000)]

primeSquares = [p * p for p in primes]

sum = 0
count = 0

def isReversiblePrimeSquare(s):
    sRev = int(str(s)[::-1])
    sRevRoot = sqrt(sRev)

    if (sRevRoot != floor(sRevRoot)):
        return False

    return sRev != s and isprime(int(sRevRoot))

for s in primeSquares:
    if (isReversiblePrimeSquare(s)):
        sum += s
        count += 1

        if (count == 50):
            break
 
print(sum)