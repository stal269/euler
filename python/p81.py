import sys

def getInitialArr(size):
    ls = []

    for i in range(0, size):
        ls.append([])

        for j in range(0, size):
            ls[i].append(0)

    return ls

def getCellVal(arr, i, j):
    if i < 0 and j == 0 or j < 0 and i == 0:
        return 0

    if i < 0 and j > 0 or j < 0 and i > 0:
        return sys.maxsize

    return arr[i][j]

def findSum(arr):
    # we want to treat each cell as a subset of the problem, 
    # each cell f(i,j) should have the formula
    # f(i,j) = val(i,j) + min {f(i-1,j),f(i,j-1)}
    sums = getInitialArr(len(arr))

    for i in range(0, len(arr)):
        for j in range(0, len(arr)):
            sums[i][j] = arr[i][j] + min(getCellVal(sums,i-1,j), getCellVal(sums,i,j-1))

    return sums[len(arr) - 1][len(arr) - 1]

matrix = [
    # [131, 673, 234, 103, 18],
    # [201, 96, 342, 965, 150],
    # [630, 803, 746, 422, 111],
    # [537, 699, 497, 121, 956],
    # [805, 732, 524, 37, 331]
 ]

f = open("p081_matrix.txt", "r")
linesStr = f.read()
lines = linesStr.split("\n")

for line in lines:
    numsStr = line.split(",")
    nums = list(map(lambda x: int(x), numsStr))
    matrix.append(nums)

print(findSum(matrix))

