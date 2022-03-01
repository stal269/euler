from math import factorial

valueBar = 1000000

def binom(n, r):
    return factorial(n) / (factorial(r) * factorial(n-r))

# (n choose r) = (n-1 choose r-1) + (n-1 choose r) according to paskal triangle
def getCellsSum(triangle, n, r):
    topLeft = triangle [n-1][r-1] if r - 1 >= 0 else 0
    topRight = triangle [n-1][r] if r <= n - 1 else 0

    return topLeft + topRight

def combinatoricSelections():
    triangle = [[1]]
    counterMillion = 0

    for n in range(1, 101):
        triangle.append([])
        
        for r in range(n + 1):
            topCellsSum = getCellsSum(triangle, n, r)
            triangle[n].append(topCellsSum)

            if topCellsSum > valueBar:
                counterMillion += 1

    return counterMillion

print(combinatoricSelections())