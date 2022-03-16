const maxNumber = 10000;
const iterationsNum = 50;

// we need to define non-lychrel
var nonLychrel = <dynamic>{};

bool isPalindrom(BigInt num) {
  return num == reverseInt(num);
}

BigInt reverseInt(BigInt num) {
  return BigInt.parse(num.toString()
    .split('')
    .reversed
    .join());
}

bool isLychrel(int num) {
  var sumSequence = <dynamic>{};
  BigInt currentSum = BigInt.from(num);

  for (var i = 1; i < iterationsNum; i++) {
    currentSum = currentSum + reverseInt(currentSum);

    if (nonLychrel.contains(currentSum) || isPalindrom(currentSum)) {
      nonLychrel.addAll(sumSequence);

      return false;
    }

    sumSequence.add(currentSum);
  }

  return true;
}

int countLychrel() {
  var count = 0;

  for (var i in List.generate(maxNumber, (i) => i)) {
    if (isLychrel(i)) count += 1;
  }

  return count;
}

void main(List<String> arguments) {
  final stopwatch = Stopwatch()..start();
  print(countLychrel());
  print('countLychrel() executed in ${stopwatch.elapsed}');
}
