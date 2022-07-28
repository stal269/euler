from utils import getPeriod
from math import floor, sqrt

print (len([i for i in range(2, 10001) if floor(sqrt(i)) != sqrt(i) if len(getPeriod(i)) % 2 == 1]))