

from itertools import permutations

internal = list(permutations([1,2,3,4,5]))
external = list(permutations([7,8,9,10]))

def convertStr(n):
    return str(n)

def buildNumber (i, e):
    gon1 = [6, i[0], i[1]]
    gon2 = [e[0], i[1], i[2]]
    gon3 = [e[1], i[2], i[3]]
    gon4 = [e[2], i[3], i[4]]
    gon5 = [e[3], i[4], i[0]]

    sum1 = sum(gon1)
    sum2 = sum(gon2)
    sum3 = sum(gon3)
    sum4 = sum(gon4)
    sum5 = sum(gon5)

    if (sum1 == sum2 and sum2 == sum3 and sum3 == sum4 and sum4 == sum5):
        digits = gon1 + gon2 + gon3 + gon4 + gon5
        return int("".join(map(convertStr, digits)))
    
    return 0

numbers = []

for i in internal:
    for e in external:
        numbers.append(buildNumber(i, e))

print(max(numbers))