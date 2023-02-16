import re
from math import floor, ceil, sqrt
import time

regex = "1\d{1}2\d{1}3\d{1}4\d{1}5\d{1}6\d{1}7\d{1}8\d{1}9\d{1}0"

min = floor(sqrt(1020304050607080900))
max = ceil(sqrt(1929394959697989990))

begin = time.time()

for i in range(min, max, 10):
    if(re.search(regex, str(i * i)) != None):
        print(i)
        break

end = time.time()

print("total program runtime is", round(end - begin, 3) * 1000, "ms")

    

