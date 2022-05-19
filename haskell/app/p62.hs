module Main where
import Data.List
import Data.Map (Map)
import qualified Data.Map as Map

concatAndSort :: [Int] -> [Int] -> [Int]
concatAndSort a b = sort (a ++ b)

cubes = [(sort (show c), [c]) | i <- [5..10000], let c = i * i * i]
cubePairs = Map.toList (Map.fromListWith (concatAndSort) cubes)

main :: IO ()
main = do
    print (head (snd (head (filter (\p -> length (snd p) == 5) cubePairs))))