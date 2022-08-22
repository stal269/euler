from sympy import sieve

primes = [i for i in sieve.primerange(500000)]

MAX_NUMBER = 1000000

def createMul():
    currentMul = 1
    idx = 0

    while currentMul * primes[idx] < MAX_NUMBER:
        currentMul *= primes[idx]
        idx += 1

    return currentMul

print(createMul())
