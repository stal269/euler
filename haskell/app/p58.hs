module Main where

import Data.Numbers.Primes (isPrime)
import GHC.Num (divInteger)
import Criterion.Main

threshold = 0.1

idx :: Integer -> Integer
idx i = i `div` 2 + i `mod` 2

lastElem :: Integer -> Integer -> Integer -> Integer
lastElem first i d = first + (i - 1) * d

seqSum :: Integer -> Integer -> Integer -> Integer
seqSum first i  d = i * (first + last) `div` 2
    where last = lastElem first i d

d1 :: Integer -> Integer
d1 i = 1 + seqSum 2 i 2

d2 :: Integer -> Integer
d2 i = (1 + 2 * seqSum 4 norI 4) - ((i `mod` 2) * lastElem 4 norI 4)
    where norI = idx i

spiralPrimesHelper :: Integer -> Integer -> Integer -> Integer
spiralPrimesHelper idx total pNum
    | pPercentage < threshold = (newTotal `divInteger` 2) + 1
    | otherwise = spiralPrimesHelper (idx+2) newTotal newPNum
    where
        (x1, x2) = (d1 idx, d1 (idx+1))
        (y1, y2) = (d2 idx, d2 (idx+1))
        newTotal = total + 4
        newPNum = pNum + toInteger (length (filter isPrime [x1,x2,y1,y2]))
        pPercentage = fromIntegral newPNum / (fromIntegral newTotal + 1)


-- main :: IO ()
main = defaultMain [
    bgroup "spiral" [
                        bench "1" $ whnf (spiralPrimesHelper 1 0) 0
                    ]
                   ]
    

