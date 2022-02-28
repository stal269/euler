import scala.util.control.Breaks

object Problem52 {

  val min = 1023456789
  val max = 1659874320

  val minNumOfDigits = 3
  val maxNumOfDigits = 10

  def digitsSet(num: Int): Set[String] = {
    num.toString.split("").toSet
  }

  def getNumberPrefix(num: Int, digits: Int): Int = {
    if (digits < 10) num / Math.pow(10.0, 10 - digits).toInt else num
  }

  def isUniqueNumbers(num: Int): Boolean = {
    digitsSet(num).size == num.toString.length
  }

  def hasPermutedMultiplies(num: Int): Boolean = {
    1.to(5).forall(mul => (digitsSet(num * mul) -- digitsSet(num)).isEmpty)
  }

  def permutedMultiples(): Int = {
    var numOfDigits = minNumOfDigits
    val digitsLoop = new Breaks

    digitsLoop.breakable {
      while (numOfDigits <= maxNumOfDigits) {
        val start = getNumberPrefix(min, numOfDigits)
        val end = getNumberPrefix(max, numOfDigits)

        val rangeResult = start.to(end)
          .filter(isUniqueNumbers)
          .find(hasPermutedMultiplies)
          .getOrElse(0)

        if (rangeResult == 0) numOfDigits += 1
        else return rangeResult
      }
    }
    0
  }

  def main(args: Array[String]) {
    val start = System.currentTimeMillis
    println(permutedMultiples())
    val end = System.currentTimeMillis
    println("solution took:", end - start)
  }
}