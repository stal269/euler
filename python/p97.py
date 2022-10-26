result = 1
counter = 0

for i in range (1, 7830458):
    result = (result * 2) % (10 ** 10)
    counter += 1

print(counter)
print((result * 28433 + 1) % (10 ** 10))

# 7939992577
