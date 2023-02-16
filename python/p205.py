pRange = range(1, 5)

pResults = [x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8 + x9
    for x1 in pRange
    for x2 in pRange 
    for x3 in pRange
    for x4 in pRange
    for x5 in pRange
    for x6 in pRange
    for x7 in pRange
    for x8 in pRange
    for x9 in pRange
]

cRange = range(1, 7)

cResults = [x1 + x2 + x3 + x4 + x5 + x6
    for x1 in cRange
    for x2 in cRange 
    for x3 in cRange
    for x4 in cRange
    for x5 in cRange
    for x6 in cRange
]

pResultP = 1 / 262144
cResultP = 1 / 46656

def getResultsCounts(results):
    resultsCounts = {}

    for r in results:
        strR = str(r)
        if resultsCounts.get(strR) == None:
            resultsCounts[strR] = 1
        else:
            resultsCounts[strR] = resultsCounts.get(strR) + 1
    
    return resultsCounts

pCounts = getResultsCounts(pResults)
cCounts = getResultsCounts(cResults)

peterWinsP = 0

for p in range(9, 37):
    strP = str(p)
    if pCounts.get(strP) == None: continue
    for c in range (6, p):
        strC = str(c)
        if cCounts.get(strC) == None: continue
        peterWinsP += (pCounts.get(strP) * pResultP) * (cCounts.get(strC) * cResultP)

print(round(peterWinsP, 7))