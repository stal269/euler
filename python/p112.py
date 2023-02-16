import sys

def isBouncy(n):
    ascending = int("".join(sorted(list(str(n)))))
    descending = int("".join(sorted(list(str(n)), reverse = True)))
    
    return n != ascending and n!= descending

numOfBouncies = 0
bounciesPercentage = 0
maxNum = 21780
requestedPercentage = 99
requestedNumber = 0

for n in range(1, sys.maxsize):
    if isBouncy(n):
        numOfBouncies += 1
        bounciesPercentage = (numOfBouncies / n) * 100

        if bounciesPercentage == requestedPercentage:
            requestedNumber = n
            break

print(requestedNumber)

# well done, can you somehow optimise so we go bellow 1 sec?
