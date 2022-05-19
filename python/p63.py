from math import floor, log10

powers = [bp for p in range (1,10000) for bp in [(b,p, b ** p) for b in range(1,10)] if floor(log10(bp[2])) + 1 == p]

print (len(powers))