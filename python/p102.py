def takeFirst(point):
    return point[0]

def getQuarter(p):
    if p[0] < 0 and p[1] > 0: return 1
    if p[0] > 0 and p[1] > 0: return 2
    if p[0] > 0 and p[1] < 0: return 3
    if p[0] < 0 and p[1] < 0: return 4

def getQuarters(p1, p2):
    quarters = {getQuarter(p1), getQuarter(p2)}
    diffX = p2[0] - p1[0]
    m = (p2[1] - p1[1]) / diffX if diffX != 0 else 'inf'
    if m == 'inf' or m == 0: return quarters
    
    srtPointsByX = sorted([p1, p2], key=takeFirst)
    n = p1[1] - m * p1[0]
    xIntersection = (-n) / m
    yIntersection = n
    
    crossX = False
    crossY = False

    if m > 0: 
        if srtPointsByX[0][0] < xIntersection  < srtPointsByX[1][0]:
            crossX = True
        if srtPointsByX[0][1] < yIntersection < srtPointsByX[1][1]:
            crossY = True
    if m < 0:
        if srtPointsByX[0][0] < xIntersection  < srtPointsByX[1][0]:
            crossX = True
        if srtPointsByX[0][1] > yIntersection > srtPointsByX[1][1]:
            crossY = True

    if crossX and crossY: quarters.add(getQuarter((xIntersection, yIntersection)))

    return quarters

f = open("p102_triangles.txt", "r")

lines = f.readlines()

count = 0

for l in lines:
    strNumbers = l.replace("\n", "").split(",")
    numbers = list(map(int, strNumbers))
    
    p1 = tuple(numbers[0:2])
    p2 = tuple(numbers[2:4])
    p3 = tuple(numbers[4:6])
    allQuarters = getQuarters(p1, p2).union(getQuarters(p2, p3)).union(getQuarters(p3, p1))
    quartersCount = len(allQuarters)

    if quartersCount == 4: count += 1
    
print(count)

