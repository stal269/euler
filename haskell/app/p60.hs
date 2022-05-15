module Main where
import Data.Numbers.Primes
import qualified Data.IntMap.Strict as Map
import Data.Set (Set)
import Data.List
import qualified Data.Set as Set

primesSpace = tail (take 10000000 primes)
primesSet = Set.fromList primesSpace
usedPrimes = take 200000 primesSpace

isPrimePair :: Int -> Int -> Bool
isPrimePair p1 p2 = Set.member result1 primesSet && Set.member result2 primesSet
    where
        p1str = show p1
        p2str = show p2
        concat1 = concat [p1str, p2str]
        concat2 = concat [p2str, p1str]
        result1 = read concat1 :: Int
        result2 = read concat2 :: Int

partitionsNum :: Int -> Int
partitionsNum num = ceiling (logBase 10 (fromIntegral num)) - 1

mergeLists :: [(Int, Int)] -> [(Int, Int)] -> [(Int, Int)]
mergeLists a [] = a
mergeLists a (item:items) = mergeLists (item:a) items

getPairsOfPrime :: Int -> Int -> Int -> [(Int, Int)] -> [(Int, Int)]
getPairsOfPrime _ 0 _ pairs = pairs
getPairsOfPrime prime partitions sep pairs
    | notPaired = getPairsOfPrime prime (partitions - 1) newSep pairs
    | isPairOfPrimes && arePaired && isValidPair = pair:pairs
    | otherwise = pairs
    where
        newSep = sep * 10
        pair = (prime `div` newSep, prime `mod` newSep)
        isPairOfPrimes = Set.member (fst pair) primesSet && Set.member (snd pair) primesSet
        arePaired = isPrimePair (fst pair) (snd pair)
        notPaired = not arePaired
        isValidPair = (fst pair) < (snd pair)

getPairs :: [Int] -> [(Int, Int)] -> [(Int, Int)]
getPairs [] pairs = pairs
getPairs (p:myPrimes) pairs = getPairs myPrimes mergedPairs
    where
        newPairs = getPairsOfPrime p (partitionsNum p) 1 []
        mergedPairs = mergeLists pairs newPairs

myPairs = getPairs usedPrimes []

seconds = map snd myPairs

triplets = [(x1, x2, x3) | (x1, x2) <- myPairs, x3 <- seconds, x3 > x2, isPrimePair x1 x3, isPrimePair x2 x3, x3 > x2]

cubs = [(x1, x2, x3, x4) | (x1, x2, x3) <- triplets, x4 <- seconds, isPrimePair x1 x4, isPrimePair x2 x4, isPrimePair x3 x4, x4 > x3]

fifths = [[x1, x2, x3, x4, x5] | (x1, x2, x3, x4) <- cubs, x5 <- seconds, isPrimePair x1 x5, isPrimePair x2 x5, isPrimePair x3 x5, isPrimePair x4 x5, x5 > x4]

main :: IO ()
main = do
    print (minimum (map sum fifths))