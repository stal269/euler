from utils import R

period = [1,2,1]

def sumOfDigits(num):
    counter = 0

    for s in str(num):
        counter += int(s)

    return counter

def add(integer, rational):
    return R(integer * rational.d + rational.n, rational.d)

def getCoeff(idx):
    coeffIdx = idx % 3
    
    # we mul 2k
    if coeffIdx == 1:
        mul = (idx // 3) + 1
        return period[coeffIdx] * mul

    return period[coeffIdx]

def exp(currI, idx):
    if idx == 0:
        return R(1, getCoeff(currI))

    coeff = getCoeff(currI)
    next = exp(currI + 1, idx - 1)

    return add(coeff, next).inverse()

print(sumOfDigits(add(2,exp(0, 98)).n))