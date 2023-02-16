import sys

NUM_OF_RECS = 2000000

minD = sys.maxsize
hT = 2
wT = 2

for h in range(2, 100):
    for w in range(2, 100):
        s1 = w * (1 + w) / 2
        sh = s1 + (h - 1) * s1
        s = h * (s1 + sh) / 2

        d = abs(s - NUM_OF_RECS)

        if d < minD:
            minD = d
            hT = h
            wT = w

print(hT * wT)