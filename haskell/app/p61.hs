module Main where
import Data.Map (Map)
import qualified Data.Map as Map
import Data.Set (Set)
import qualified Data.Set as Set

isPointer :: Int -> Int -> Bool
isPointer a b = prefix == postfix
    where
        prefix = b `div` 100
        postfix = a `mod` 100

difference :: Char -> Char -> Char -> Char -> Char -> Char -> Bool
difference x1 x2 x3 x4 x5 x6 = Set.size (Set.fromList [x1, x2, x3, x4, x5, x6]) == 6

getValue :: Maybe [(Int, Char)] -> [(Int, Char)]
getValue (Just l) = l
getValue (Nothing) = []

triangles = [x | i <- [45,46..140], let x =  i * (i + 1) `div` 2 ]
squares = [x | i <- [32,33..99], let x = i * i]
pentagonals = [x | i <- [26,27..81], let x = i * (3 * i - 1) `div` 2]
hexagonals = [x | i <- [23,24..70], let x = i * (2 * i - 1)]
heptagonals = [x | i <- [21,22..63], let x = i * (5 * i - 3) `div` 2]
octagonals = [x | i <- [19,20..58], let x = i * (3 * i - 2)]

allLists = [(triangles, '1'), (squares, '2'), (pentagonals, '3'), (hexagonals, '4'), (heptagonals, '5'), (octagonals, '6')]

allListPairs = [(l1, l2)| l1 <- allLists, l2 <- allLists, l1 /= l2]

allPairs = concat [pairs | lp <- allListPairs, let pairs = [((x, idx1), [(y, idx2)]) | x <- fst (fst lp), y <- fst (snd lp), let idx1 = snd (fst lp), let idx2 = snd (snd lp), isPointer x y]]

listsMap = Map.fromListWith (++) allPairs

sixths = Set.fromList (map sum [[fst x1, fst x2, fst x3, fst x4, fst x5, fst x6] | x1 <- Map.keys listsMap,
    x2 <- getValue (Map.lookup x1 listsMap),
    x3 <- getValue (Map.lookup x2 listsMap),
    x4 <- getValue (Map.lookup x3 listsMap),
    x5 <- getValue (Map.lookup x4 listsMap),
    x6 <- getValue (Map.lookup x5 listsMap),
    elem x1 (getValue (Map.lookup x6 listsMap)),
    difference (snd x1) (snd x2) (snd x3) (snd x4) (snd x5) (snd x6)])

main :: IO ()
main = do
    print (sixths)