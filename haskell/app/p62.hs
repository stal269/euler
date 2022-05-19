module Main where
import Math.NumberTheory.Roots
import Data.List
import Data.Set (Set)
import qualified Data.Set as Set
import Data.Map (Map)
import qualified Data.Map as Map

concatAndSort :: [Int] -> [Int] -> [Int]
concatAndSort a b = sort (a ++ b)

cubes = [(sort (show c), [c]) | i <- [5..10000], let c = i * i * i]
cubePairs = Map.toList (Map.fromListWith (concatAndSort) cubes)

main :: IO ()
main = do
    print (head (snd (head (filter (\p -> length (snd p) == 5) cubePairs))))