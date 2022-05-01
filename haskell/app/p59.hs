module Main where
import System.IO
import Data.Char
import Data.List
import Data.Bits

normalize :: String -> [Int]
normalize content = bytes
    where
        commaStrip = map (\c -> if c == ',' then ' ' else c) content
        strBytes = words commaStrip
        bytes = map (\byte -> (read byte::Int)) strBytes

decrypt :: [Int] -> [Int] -> [Int] -> [Int]
decrypt [] _ plaintext = plaintext
decrypt (c1:c2:c3:c) key plaintext = decrypt c key (concat [plaintext, decryption])
    where decryption = zipWith (xor) [c1,c2,c3] key

splitCipher :: [Int] -> [Int] -> [Int] -> [Int] -> [[Int]]
splitCipher [] l1 l2 l3 = [l1, l2, l3]
splitCipher (c1:c2:c3:c) l1 l2 l3 = splitCipher c (c1:l1) (c2:l2) (c3:l3)

getFreq :: [Int] -> [(Int, Int)] -> [Int] -> [(Int, Int)]
getFreq [] freqs _ = freqs
getFreq _ freqs [] = freqs
getFreq (l:letters) freqs cipher = getFreq letters ((l, freq):freqs) cipher
    where
        filteredCipher = filter (\c -> c == l) cipher
        freq = length filteredCipher

getSplitCipher :: [Char] -> [[Int]]
getSplitCipher cipher = splitCipher (normalize cipher) [] [] []

getMostFreq :: [(Int, Int)] -> Int
getMostFreq pairs = fst (last (sortOn snd pairs))

getKey :: [Char] -> [Int]
getKey cipher = key
    where
        pairsLists = map (getFreq [0..127] [])  (getSplitCipher cipher)
        mostFreq = map getMostFreq pairsLists
        key = map (\c -> c `xor` 32) mostFreq

main :: IO ()
main = do
    h <- openFile "resources/p059_cipher.txt" ReadMode
    cipher <- hGetContents h
    print (sum (decrypt (normalize cipher) (getKey cipher) []))
