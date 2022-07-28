from utils import R, add

per = [1,2,1]

def sumOfDigits(num):
    counter = 0

    for s in str(num):
        counter += int(s)

    return counter



def getCoeff(idx, period):
    coeffIdx = idx % 3
    
    # we mul 2k
    if coeffIdx == 1:
        mul = (idx // len(period)) + 1
        return period[coeffIdx] * mul

    return period[coeffIdx]

def exp(currI, idx, period):
    if idx == 0:
        return R(1, getCoeff(currI, period))

    coeff = getCoeff(currI, period)
    next = exp(currI + 1, idx - 1, period)

    return add(coeff, next).inverse()

print(sumOfDigits(add(2,exp(0, 98, per)).n))