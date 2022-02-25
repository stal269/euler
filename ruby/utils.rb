#!/usr/bin/ruby -w
require 'set';

def isPrime(num)
    if num < 2 
        return false;
    end

    divisor = 2;
    square = Math.sqrt(num);

    while divisor < square
        if num % divisor == 0
            return false;
        end

        divisor += 1;
    end

    return true;
end

def eSieve(min, limit)
    isComposite = Array.new(limit, false);
    p = 2;

    while p <= limit
        mul = 2;
        currentMul = mul * p;

        while currentMul < limit
            isComposite[currentMul] = true;
            mul += 1;
            currentMul = mul * p;
        end
        
        p += 1;

        while p < limit && isComposite[p]
            p += 1;
        end
        
    end

    primes = [];

    for n in min..(limit - 1)
        if !isComposite[n]
            primes.push(n);
        end
    end

    return primes.to_set();
end



