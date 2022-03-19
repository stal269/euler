const fs = require('fs');

const FILE_NAME = 'poker.txt';
const LAST_CARD = 4;
const PLAYER1_WIN = 1;
const PLAYER2_WIN = 2;
const TIE = 3;
const HIGH_CARD = 1;
const ONE_PAIR = 2;
const TWO_PAIRS = 3;
const THREE_OF_KIND = 4;
const STRAIGHT = 5;
const FLUSH = 6;
const FULL_HOUSE = 7;
const FOUR_OF_KIND = 8;
const STRAIGHT_FLUSH = 9;
const ROYAL_FLUSH = 10;

const PAIRS = 'pairs';
const THREE = 'three';
const FOUR = 'four';

const cardCountsToFeatures = {
    2: PAIRS,
    3: THREE,
    4: FOUR
}

const STRAIGHT_FEATURE = 'straight';

const cardsPriority = {
    '2': 0,
    '3': 1,
    '4': 2,
    '5': 3,
    '6': 4,
    '7': 5,
    '8': 6,
    '9': 7,
    'T': 8,
    'J': 9,
    'Q': 10,
    'K': 11,
    'A': 12,
};

const removeMultiplesFromHand = (hf) => {
    const mulValues = [
        ...hf.pairs ? hf.pairs: [],
        ...hf.three ? hf.three: [],
        ...hf.four ? hf.four: []
    ];

    return hf.hand.filter(card => !mulValues.find(val => card[0] === val));
}

const compareCards = (hand1, hand2) => {
    let i = hand1.length - 1;
    while (i > 0 && cardsPriority[hand1[i][0]] === cardsPriority[hand2[i][0]]) i--;
    const player1CardRank = cardsPriority[hand1[i][0]];
    const player2CardRank = cardsPriority[hand2[i][0]];
    if (player1CardRank === player2CardRank) return TIE;

    return cardsPriority[hand1[i][0]] > cardsPriority[hand2[i][0]] ? 
        PLAYER1_WIN :
        PLAYER2_WIN;
}

const compareSingleCardSeq = (hf1, hf2, seqType) => {
    if(cardsPriority[hf1[seqType][0]] !== cardsPriority[hf2[seqType][0]])
    return cardsPriority[hf1[seqType][0]] > cardsPriority[hf2[seqType][0]];
        return compareCards(removeMultiplesFromHand(hf1), removeMultiplesFromHand(hf2)) === 1;
}

const tieBrakers = {
    [HIGH_CARD]: (hf1, hf2) => compareCards(hf1.hand, hf2.hand) === PLAYER1_WIN,
    [ONE_PAIR]: (hf1, hf2) => compareSingleCardSeq(hf1, hf2, PAIRS),
    [TWO_PAIRS]: (hf1, hf2) => {
        const pairsCompareResult = compareCards(hf1.hand, hf2.hand);
        if (pairsCompareResult === 1) return true;
        else if (pairsCompareResult == 2) return false;
        
        return compareCards(removeMultiplesFromHand(hf1), removeMultiplesFromHand(hf2)) === 1;
    },
    [THREE_OF_KIND]: (hf1, hf2) => compareSingleCardSeq(hf1, hf2, THREE_OF_KIND),
    [STRAIGHT]: (hf1, hf2) => cardsPriority[hf1.hand[LAST_CARD][0]] > cardsPriority[hf2.hand[LAST_CARD][0]],
    [FLUSH]: (hf1, hf2) => cardsPriority[hf1.hand[LAST_CARD][0]] > cardsPriority[hf2.hand[LAST_CARD][0]],
    [FULL_HOUSE]: (hf1, hf2) => compareSingleCardSeq(hf1, hf2, THREE) || 
            compareSingleCardSeq(hf1, hf2, PAIRS),
    [FOUR_OF_KIND]: (hf1, hf2) => compareSingleCardSeq(hf1, hf2, FOUR_OF_KIND),
    [STRAIGHT_FLUSH]: (hf1, hf2) => 
        cardsPriority[hf1.hand[LAST_CARD][0]] > cardsPriority[hf2.hand[LAST_CARD][0]]
}

const cardsComparator = (c1, c2) => {
    const value1 = cardsPriority[c1[0]];
    const value2 = cardsPriority[c2[0]];

    return value1 - value2;
}

const isStraight = (hand) => {
    let first = 0; let second = 1;
    let isSeries = true;

    while (second <= LAST_CARD) {
        if (cardsPriority[hand[second][0]] - cardsPriority[hand[first][0]] !== 1) {
            isSeries = false;
            break;
        }

        first++; second++;
    }

    return isSeries;
}

getMulitpleCardFeatures = (hand) => {    
    const cardCounts = hand.reduce((prev, curr) => {
        const cardValue = curr[0];

        if (!prev[cardValue]) prev[cardValue] = 1;
        else prev[cardValue]++;

        return prev;
    }, {});

    return Object.keys(cardCounts).reduce((prev, curr) => {
        const count = cardCounts[curr];

        if(count >= 2) 
            prev[cardCountsToFeatures[count]] = prev[cardCountsToFeatures[count]] ? 
            [...prev[cardCountsToFeatures[count]], curr] :
            [curr]
        
        return prev;
    }, {});
}

const isFlush = (hand) => {
    const firstCardType = hand[0][1];

    return hand.slice(1).every(card => card[1] === firstCardType)
}

const getHandFeatures = (hand) => {
    let handFeatures = {};

    if (isStraight(hand)) handFeatures[STRAIGHT_FEATURE] = hand[LAST_CARD][0];

    return {
        ...handFeatures,
        hand,
        maxValue: hand[LAST_CARD][0],
        ...getMulitpleCardFeatures(hand),
        isFlush: isFlush(hand)
    };
}

const getHandRank = (handFeatures) => {
    if (handFeatures[STRAIGHT_FEATURE]) {
        if(handFeatures.isFlush) {
            if(handFeatures.maxValue === cardsPriority.A) return ROYAL_FLUSH
            
            return STRAIGHT_FLUSH;
        }

        return STRAIGHT;
    }

    if(handFeatures[FOUR]) return FOUR_OF_KIND;

    if(handFeatures[THREE]) {
        if(handFeatures[PAIRS]) {
            return FULL_HOUSE;
        }

        return THREE_OF_KIND
    }

    if(handFeatures.isFlush) return FLUSH;

    if (handFeatures[PAIRS]) {
        if(handFeatures[PAIRS] && handFeatures[PAIRS].length === 2) return TWO_PAIRS;

        return ONE_PAIR;
    }

    return HIGH_CARD;
}

const computePlayer1Wins = (lines => {
    let player1Wins = 0;

    lines.forEach(line => {
        const cards = line.split(' ');
        const p1Hand = cards.slice(0, 5);
        const p2Hand = cards.slice(5, 10);
    
        p1Hand.sort(cardsComparator);
        p2Hand.sort(cardsComparator);

        const hand1Features = getHandFeatures(p1Hand);
        const hand2Features = getHandFeatures(p2Hand);

        const hand1Rank = getHandRank(hand1Features);
        const hand2Rank = getHandRank(hand2Features);

        if (hand1Rank > hand2Rank) {
            player1Wins++;
        } else if(hand1Rank === hand2Rank && tieBrakers[hand1Rank](hand1Features, hand2Features)){
            player1Wins++;
        }
    });
    

    console.log(`player 1 won ${player1Wins} games`);
})

fs.readFile(FILE_NAME, 'utf8', (err, data) => {
    const lines = data.split('\n');
    computePlayer1Wins(lines);
});