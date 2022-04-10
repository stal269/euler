{-# OPTIONS_GHC -Wno-incomplete-patterns #-}
{-# OPTIONS_GHC -Wno-overlapping-patterns #-}
module Main where

import Data.Numbers.Primes
import Data.List ()
import Data.Fixed
import qualified Data.Set as Set
import GHC.Base (divInt)
import GHC.Num (divInteger)

maxSide = 1000000
threshold = 0.1

sumPrefix :: [Integer] -> Int -> Integer
sumPrefix list size = sum (1:take size list)

mapElemToSum :: [Integer] -> [Integer]
mapElemToSum list = [x | i <- [1..(length list)], let x = sumPrefix list i] 

diagonal_1 = take maxSide [1 + d | i <- [2, 4..], let d = sum [2, 4..i]]

diagonal_2 = mapElemToSum (take maxSide (concat [[i, i] | i <- [4, 8..]]))

spiralPrimesHelper :: [Integer] -> [Integer] -> Integer -> Integer -> Integer
spiralPrimesHelper [] [] total pNum = total
spiralPrimesHelper (x1:x2:xs) (y1:y2:ys) total pNum
    | pPercentage < threshold = (newTotal `divInteger` 2) + 1
    | otherwise = spiralPrimesHelper xs ys newTotal newPNum
    where
        newTotal = total + 4
        newPNum = pNum + toInteger (length (filter isPrime [x1,x2,y1,y2]))
        pPercentage = fromIntegral newPNum / (fromIntegral newTotal + 1)

main :: IO ()
main = print (spiralPrimesHelper diagonal_1 diagonal_2 0 0)

