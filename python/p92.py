

def sumOfSquares(n):
    digits = list(map(lambda x: int(x), [*str(n)]))
    squares = list(map(lambda x: x * x, digits))

    return sum(squares)

dict89 = set()
dict1 = set()

def is89(n):
    nextNum = n

    while nextNum not in {89, 1}:
        if nextNum in dict89:
            print("hit89")
            return True

        if nextNum in dict1:
            print("hit1")
            return False

        nextNum = sumOfSquares(nextNum)

    # print(nextNum)

    if nextNum == 89:
        dict89.add(n)
        return True
    else:
        dict1.add(n)
        return False

counter = 0


for i in range(1, 10000001):
    if is89(i):
        counter += 1

print(counter)