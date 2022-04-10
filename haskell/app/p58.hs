module Main where

import Data.Numbers.Primes
import GHC.Num (divInteger)

threshold = 0.1

idx :: Integer -> Integer
idx i = i `div` 2 + i `mod` 2

lastElem :: Integer -> Integer -> Integer -> Integer
lastElem first i d = first + (i - 1) * d

seqSum :: Integer -> Integer -> Integer -> Integer
seqSum first i  d = i * (first + last) `div` 2
    where last = lastElem first i d

diagonal_1_elem :: Integer -> Integer
diagonal_1_elem i = 1 + seqSum 2 i 2

diagonal_2_elem :: Integer -> Integer
diagonal_2_elem i = (1 + 2 * seqSum 4 norI 4) - ((i `mod` 2) * lastElem 4 norI 4)
    where norI = idx i

spiralPrimesHelper :: Integer -> Integer -> Integer -> Integer
spiralPrimesHelper idx total pNum
    | pPercentage < threshold = (newTotal `divInteger` 2) + 1
    | otherwise = spiralPrimesHelper (idx+2) newTotal newPNum
    where
        (x1, x2) = (diagonal_1_elem idx, diagonal_1_elem (idx+1))
        (y1, y2) = (diagonal_2_elem idx, diagonal_2_elem (idx+1))
        newTotal = total + 4
        newPNum = pNum + toInteger (length (filter isPrime [x1,x2,y1,y2]))
        pPercentage = fromIntegral newPNum / (fromIntegral newTotal + 1)

main :: IO ()
main = print (spiralPrimesHelper 1 0 0)
    

