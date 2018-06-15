import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

  /**
   *
   * @param n the divisor
   * @param max maximum number
   * @return the result
   */
  public static int sum(int n, int max){
    int p = (max-1) / n; //p is the number of elements divisible by n
    return n * p * ( p + 1 ) / 2;
  }

  /**
   * @param n max value number
   * @return
   */
  public static int fiboEvenSum(int n){

    int first = 1;
    int second = 2;

    int third = first + second;

    int sumEven = second;

    while(third <= n){

      if(third % 2 == 0){

        sumEven += third;
      }

      first = second;
      second = third;


      third = first + second;

    }

    return sumEven;
  }

  public static boolean isPrime(long candidate){

    if(candidate < 2){
      return false;
    }

    long divisor = 2;

    long square = (long) Math.sqrt(candidate);



    while(divisor <= square){
      if(candidate % divisor == 0){
        return false;
      }
      divisor++;
    }
    return true;
  }

  /**
   * finds the largest prime that composes n
   * @return
   */
  public static long findLargetPrime(long n){
    long candidate = (long) Math.sqrt(n);
    while(candidate > 2){
      if(n % candidate == 0 && isPrime(candidate)){
        return candidate;
      }
      candidate--;
    }
    return 2;
  }

  private static boolean isPalindrom(long n){
    long num = n;
    long rev = 0;
    long dig;
    while (num > 0)
    {
      dig = num % 10;
      rev = rev * 10 + dig;
      num = num / 10;
    }
    return n == rev;
  }

  public static void largetPalindrom3digitNumbers(){

    long num1 = 101;
    long num2 = 101;

    long maxPal = 0;

    long mul =0;

    while (num1 < 1000){

      while(num2 < 1000){

        mul = num1 * num2;

        if(isPalindrom(mul) && mul > maxPal){
          System.out.println(mul);
          maxPal = mul;
        }
        num2++;
      }
      num2 = num1;
      num1++;
    }
    System.out.println(maxPal);
  }

  public static long sumSquareDifference(int maxNum){

    long sumOfSquares = (maxNum *(maxNum+1)*(2*maxNum+1))/6;

    long sum = (maxNum * (1 + maxNum)) / 2;
    long squareOfSum = (long) Math.pow(sum, 2);

    return squareOfSum - sumOfSquares;

  }

  public static long the10001Prime(){
    int primeNum = 1;
    long candidate = 3;

    long finalPrime = 2;

    while(primeNum < 10001){
      if(isPrime(candidate)){
        primeNum++;
        finalPrime = candidate;
      }
      candidate += 2;
    }
    return finalPrime;
  }

  private static long evalMul(String rep){
    long mul = 1;
    int repLength = rep.length();
    int currentNum;
    for(int i = 0; i < repLength; i++){
      currentNum = Character.digit(rep.charAt(i), 10);;
      mul *= currentNum;
    }
    return mul;
  }

  public static long largetMul13Consecutive(String numRep){

    int endIdx = 13;
    int startIdx = 0;

    String current;

    long currentMul;
    long maxMul = 0;

    int totalNumLen = numRep.length();

    for(;endIdx < totalNumLen; endIdx++,startIdx++){

      current = numRep.substring(startIdx,endIdx);
      if(!current.contains("0")){

        currentMul = evalMul(current);

        if(currentMul > maxMul){
          maxMul = currentMul;
        }
      }
    }

    return maxMul;

  }

  private static boolean checkCondition(long a, long b){
    return 1000 * (a + b) - a* b == 500000;

  }

  public static long ourTriplet(int num){

    long a = 1;

    long b;

    long finalA = 1;
    long finalB = 1;
    long finalC;

    boolean found = false;

    while(!found){ //run on a
      b = a + 1;
      while (b < num && !found){ //run on b

        if(checkCondition(a,b)){
          found = true;
          finalA = a;
          finalB = b;
        }
        b++;
      }
      a++;
    }
    finalC = 1000 - finalA - finalB;

    return finalA * finalB * finalC;

  }

  public static long sumOfPrimesBelow(long limit){
    long current =3;
    long sum = 2;
    while(current < limit){
      if(isPrime(current)){
        sum += current;
      }
      current += 2;
    }
    return sum;
  }

  private static long mul4Numbers(long e1, long e2, long e3, long e4){

    if(e1 > 0 && e2 > 0 && e3 > 0 && e4 > 0){
      return e1 * e2 * e3 * e4;
    }
    return 0;
  }

  public static long findLargetMulGrid(int [][] grid){

    long maxMul = 0;
    long currentMul;

    int numOfValidSpots = grid.length - 4;

    for(int i = 0; i < numOfValidSpots; i++){

      for(int j = 0; j < numOfValidSpots; j++){

        currentMul = mul4Numbers(grid[i][j], grid[i][j+1], grid[i][j+2], grid[i][j+3]);
        if(currentMul > maxMul){
          maxMul = currentMul;
        }
        currentMul = mul4Numbers(grid[i][j], grid[i+1][j], grid[i+2][j], grid[i+3][j]);
        if(currentMul > maxMul){
          maxMul = currentMul;
        }
        currentMul = mul4Numbers(grid[i][j], grid[i+1][j+1], grid[i+2][j+2], grid[i+3][j+3]);
        if(currentMul > maxMul){
          maxMul = currentMul;
        }
        if(j >= 3){
          currentMul = mul4Numbers(grid[i][j], grid[i+1][j-1], grid[i+2][j-2], grid[i+3][j-3]);
          if(currentMul > maxMul){
            maxMul = currentMul;
          }
        }
      }
    }
    return maxMul;
  }



  public static int countDivisors(long num){

    int currentNumOfDivisors = 1;

    int currentDivisor = 2;

    int powerOfDivisor = 0;

    while(num > 1){
      while(num % currentDivisor == 0){

        System.out.println(num);
        powerOfDivisor++;
        num /= currentDivisor;

      }
      currentDivisor++;
      currentNumOfDivisors *= (powerOfDivisor + 1);
      powerOfDivisor = 0;
    }
    return currentNumOfDivisors;




  }

  public static long highestTriangleDivisible(int numOfDivisors){

    int currentNumOfDivisors = 0;

    long currentTriangular = 1;

    long currentNatural = 2;

    while(currentNumOfDivisors < numOfDivisors){

      currentTriangular += currentNatural;
      currentNatural++;

      currentNumOfDivisors = countDivisors(currentTriangular);

    }

    return currentTriangular;

  }

  private static String [] parseNumbersRep(String rawRep){
    String [] numberCutTo10 = new String[100];
    int startIdx = 0;
    int endIdx = 50;

    for(int i = 0; i < 100; i++,startIdx+=50,endIdx+=50){
      numberCutTo10[i] = rawRep.substring(startIdx, endIdx);
      System.out.println(numberCutTo10[i]);
    }

    return numberCutTo10;
  }

  public static String sumOfTenDigits(String rawRep){
    String [] numberCutTo10 = parseNumbersRep(rawRep);
    double totalSum = 0.0;
    double currentNum;
    for(int i =0; i < numberCutTo10.length; i++){
      currentNum = Double.parseDouble(numberCutTo10[i]);
      totalSum += currentNum;
    }
    System.out.println("total sum: " + totalSum);
    String sumStr = Double.toString(totalSum);
    return sumStr.substring(0 , 11);
  }

  public static int LongestCollatzGenerator() throws FileNotFoundException {
    int maxSeqLength = 0;
    int maxGenerator = 0;

    for(int candidate = 2; candidate <= 30; candidate++){

      int seqLength = 0;
      long nextSeqNum = candidate;

      while(nextSeqNum > 1){
        seqLength++;
        //System.out.println(nextSeqNum);
        if((nextSeqNum % 2) == 0){
          nextSeqNum = nextSeqNum / 2;
        }
        else{
          nextSeqNum = nextSeqNum * 3 + 1;
        }


      }

      System.out.println("candidate: " + candidate + " , seqLength: " + seqLength);

      if(seqLength > maxSeqLength){
        maxSeqLength = seqLength;
        maxGenerator = candidate;
      }

      //candidate++;

    }
    return maxGenerator;
  }

  public static long computeLatticePaths(int gridSize){

    long [][] grid = new long[gridSize][gridSize];
    long knownVal = 2;

    grid[gridSize-1][gridSize-1] = knownVal;
    knownVal++;

    for (int i = gridSize-2; i >= 0; i--){
      grid[gridSize-1][i] = knownVal;
      grid[i][gridSize-1] = knownVal;
      knownVal++;

    }

    for(int i = gridSize-2; i >= 0; i--){ //dynamic programming
      for (int j = gridSize-2; j >= 0; j--){

        grid[i][j] = grid[i+1][j] + grid[i][j+1];

      }
    }

    return grid[0][0];

  }

  public static void countSumDigits(){
    String sum = "1";
    String newSum = "";
    long sumOfDigits = 0;
    int cuurentNum;
    int carry = 0;
    for(int i = 0; i < 1000; i++){
      //System.out.println("dasd");
      //newSum = sum.substring(0);
      for(int j = sum.length()-1; j >= 0; j--){
        //System.out.println("j: " + j);
        cuurentNum = Integer.parseInt(sum.substring(j,j+1));
        //System.out.println("cuurentNum: " + cuurentNum);
        cuurentNum = cuurentNum * 2 + carry;
        if(cuurentNum > 9){
          carry = 1;
          cuurentNum -= 10;
        }
        else{
          carry = 0;
        }
        newSum = cuurentNum + newSum;
        //System.out.println("newSum: " + newSum);
      }

      sum = carry + newSum;
      newSum = "";
      //System.out.println("sum: " + sum);

    }
    int dig;
    //System.out.println(sum);
    for(int i = 0; i < sum.length(); i++){
      dig = Integer.parseInt(sum.substring(i, i+1));
      sumOfDigits += dig;
    }
    System.out.println(sumOfDigits);

  }

  private static void print(long[][]arr){

    for(int i = 0; i < arr.length; i++){

      for(int j = 0; j < arr[i].length; j++){
        System.out.print(" " + arr[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static long computeTriangleSums(long[][] triangle, long[][]maxSums){

    maxSums[0][0] = triangle[0][0];

    long maxSum = 0;
    int maxNumInLastRowIdx = 0;

    int lenOfLine = 2;

    for(int i = 1; i < maxSums.length; i++){

      for(int j = 0; j < lenOfLine; j++){

        if(j > 0 && j < lenOfLine - 1){

          maxSums[i][j] = Math.max(triangle[i][j] + maxSums[i-1][j-1], triangle[i][j] + maxSums[i-1][j]);
        }
        else if(j == lenOfLine - 1){

          maxSums[i][j] = triangle[i][j] + maxSums[i-1][j-1];

        }
        else{
          maxSums[i][j] = triangle[i][j] + maxSums[i-1][j];
        }

      }
      lenOfLine++;
    }

    print(maxSums);

    for(int j = 0; j < maxSums.length; j++){
      if(maxSums[maxSums.length-1][j] > maxSum){
        maxSum = maxSums[maxSums.length-1][j];
        maxNumInLastRowIdx = j;
      }
    }



    return maxSums[maxSums.length-1][maxNumInLastRowIdx];

  }

  public static long computeSundays(){
    HashMap<Integer, Integer> monthsToNumOfDays = new HashMap<Integer, Integer>();
    monthsToNumOfDays.put(1, 31);
    monthsToNumOfDays.put(2, 28); //need to be changed every four years to 29 (excluding the year 1900 for compensating the error occurring every 400 years)
    monthsToNumOfDays.put(3, 31); monthsToNumOfDays.put(4, 30); monthsToNumOfDays.put(5, 31); monthsToNumOfDays.put(6, 30); monthsToNumOfDays.put(7, 31);
    monthsToNumOfDays.put(8, 31); monthsToNumOfDays.put(9, 30); monthsToNumOfDays.put(10, 31); monthsToNumOfDays.put(11, 30); monthsToNumOfDays.put(12, 31);

    int numOfSundays = 0;

    int currentDay = 2;
    int nextDay;
    int currentMonth = 1;
    int currentYear = 1900;

    int daysToAdd;

    //forward us to the start of the period of interest
    while (currentYear != 1901){

      daysToAdd = monthsToNumOfDays.get(currentMonth) - 28;
      nextDay = daysToAdd + currentDay;
      if(nextDay > 7){

        currentDay = nextDay - 7;
      }
      else{
        currentDay = nextDay;
      }
      currentMonth++;
      if(currentMonth > 12){
        currentYear++;
        currentMonth = 1;
      }

    }


    //start counting Sundays
    while(currentYear != 2001){
      daysToAdd = monthsToNumOfDays.get(currentMonth) - 28;
      nextDay = daysToAdd + currentDay;
      if(nextDay > 7){

        currentDay = nextDay - 7;
      }
      else{
        currentDay = nextDay;
      }
      if(currentDay == 1){
        numOfSundays++;
      }
      currentMonth++;
      if(currentMonth > 12){
        currentYear++;
        currentMonth = 1;

        if(currentYear % 4 == 0){
          monthsToNumOfDays.put(2, 29);
        }
        else {
          int daysOfFeb = monthsToNumOfDays.get(2);
          if(daysOfFeb > 28){

            monthsToNumOfDays.put(2, 28);
          }
        }
      }


    }

    return numOfSundays;
  }

  public static long facDigitSum(long n){
    BigInteger currentFactorial = BigInteger.valueOf(1);
    while(n > 1){
      currentFactorial =  currentFactorial.multiply(BigInteger.valueOf(n));
      System.out.println(n);
      n = n - 1;
    }
    long sumOfDigits = 0;
    int digit;
    //int count = 1;
    while (currentFactorial.doubleValue() > 0.0){
      //System.out.println(currentFactorial % 10);
      digit = currentFactorial.mod( BigInteger.valueOf(10)).intValue();
      //System.out.println(count);
      currentFactorial = currentFactorial.divide(BigInteger.valueOf( 10));
      //System.out.println(currentFactorial);
      sumOfDigits += digit;
      //count++;

    }

    return sumOfDigits;
  }



  private static int sumOfDivs(int n){
    int sumOfDivs = 0;

    long candidate =1;
    while(candidate <= n/2){
      if(n % candidate == 0){
        sumOfDivs += candidate;
      }
      candidate++;
    }
    return sumOfDivs;
  }

  public static long sumOfAmicable(int n){

    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
    long sumOfAmicable = 0;

    int current = 2;

    while(current < n){

      int sumOfDivsCurrent = sumOfDivs(current);

      if(cache.containsKey(current)){

        int previousAmicableCandidate = cache.get(current);

        if(previousAmicableCandidate == sumOfDivsCurrent){
          sumOfAmicable += (current + previousAmicableCandidate);
        }

      }
      else{

        cache.put(sumOfDivsCurrent, current);
      }
      current++;
    }



    return sumOfAmicable;
  }

  public static long sumNamesScores(String [] names){

    HashMap<Character, Integer> lexVals = new HashMap<Character, Integer>();

    lexVals.put('A',1); lexVals.put('B',2); lexVals.put('C',3); lexVals.put('D',4); lexVals.put('E',5); lexVals.put('F',6); lexVals.put('G',7); lexVals.put('H',8);
    lexVals.put('I',9); lexVals.put('J',10); lexVals.put('K',11); lexVals.put('L',12); lexVals.put('M',13); lexVals.put('N',14); lexVals.put('O',15);
    lexVals.put('P',16); lexVals.put('Q',17); lexVals.put('R',18); lexVals.put('S',19); lexVals.put('T',20); lexVals.put('U',21); lexVals.put('V',22);
    lexVals.put('W',23); lexVals.put('X',24);lexVals.put('Y',25); lexVals.put('Z',26);

    long sum = 0;

    String currentName;


    for(int i = 0; i < names.length; i++){

      currentName = names[i];
      int alphVal = 0;
      char currChar;

      for(int j = 0; j < currentName.length(); j++){
        currChar = currentName.charAt(j);

        if(lexVals.containsKey(currChar)){

          alphVal += lexVals.get(currChar);
        }

      }

      sum += (i+1) * alphVal;

    }

    return sum;

  }

  private static ArrayList<Integer> getAbundantNumbers(){


    ArrayList<Integer> nums = new ArrayList<Integer>();
    int sumOfDivs;


    for(int current = 2; current <= 28123; current++){

      sumOfDivs = sumOfDivs(current);
      if(sumOfDivs > current){
        nums.add(current);
      }

    }

    return nums;

  }

  private static boolean isPairSumExists(ArrayList <Integer> nums, int sum){

    int left = 0;
    int right = nums.size() - 1;

    int sumOfPair;

    while(left <= right){

      sumOfPair = nums.get(left) + nums.get(right);

      if(sumOfPair == sum){
        return true;
      }

      else if(sumOfPair < sum){
        left++;
      }
      else{
        right--;
      }

    }
    return false;

  }


  public static long nonAbundantSums(){
    int sum =0;

    ArrayList<Integer> nums = getAbundantNumbers();

    System.out.println(isPairSumExists(nums, 23));

    for(int current = 1; current <= 28123; current++){

      if(!isPairSumExists(nums, current)){
        sum += current;
      }

    }

    return sum;
  }

  private static void printArr(int [] arr){

    if(arr[arr.length-1] == 1000000){

      for(int i = 0; i < arr.length-1; i++){
        System.out.print(arr[i]);
      }
      System.out.println();
    }
  }

  public static void millPerm(int[] nums, boolean [] taken, int index){

    if(index == nums.length-1){

      nums[index]++;
      printArr(nums);
      return;
    }

    for(int i = 0; i < nums.length-1; i++){

      if(!taken[i]){
        nums[index] = i;
        taken[i] = true;
        millPerm(nums,taken,index+1);
        taken[i] = false;

      }

    }


  }

  private static int  numOfDigitsBigInteger(BigInteger big){

    BigInteger bigCopy = big.add(BigInteger.valueOf(0));

    BigInteger zero = BigInteger.valueOf(0);

    int numOfDigits = 0;

    while(!bigCopy.equals(zero)){
      numOfDigits++;
      bigCopy = bigCopy.divide(BigInteger.valueOf(10));

    }

    return numOfDigits;

  }


  public static long fibo(){
    BigInteger pre = BigInteger.valueOf(1);
    BigInteger current = BigInteger.valueOf(1);
    BigInteger next;
    long index = 2;

    int numOfDigits = 0;

    while(numOfDigits < 1000){

      next = pre.add(current);
      pre = current.add(BigInteger.valueOf(0));
      current = next.add(BigInteger.valueOf(0));
      index++;

      numOfDigits = numOfDigitsBigInteger(current);
    }

    return index;


  }

  private static int lengthOfCycle(String s){

    String afterDec = s.split("\\.")[1];

    String currentCycle="";


    int idx = afterDec.startsWith("00") ? 3 : 1;

    int nextCycleStart;

    while(idx < afterDec.length()){

      currentCycle = currentCycle + afterDec.charAt(idx - 1); //current cycle updated, now see if it is somewhere ahead and what is the distance

      nextCycleStart = afterDec.indexOf(currentCycle, idx);

      if(nextCycleStart < 0){
        currentCycle="";
      }

      if(nextCycleStart == idx){ //if the distance is zero than the seq ended and we need to consider it's length

        //System.out.println(currentCycle.length());

        if(currentCycle.length() == 982){
          System.out.println(currentCycle);
        }

        return currentCycle.length();

      }

      idx++;

    }
    return 0;
  }






  public static double reciprocalCycles(double lim){


    String decStr = "";

    int length;

    int maxLength = 0;
    double maxDeno = 2.0;

    double d = 2.0;

    BigDecimal bigOne = BigDecimal.valueOf(1.0);

    while(d < lim){

      BigDecimal bigD = BigDecimal.valueOf(d);

      decStr = bigOne.divide(bigD, 3000, RoundingMode.HALF_UP).toPlainString();

      length = lengthOfCycle(decStr);

      if(length > maxLength){

        maxLength = length;
        maxDeno = d;
        System.out.println("d: " + d + " , length: " + maxLength);



      }

      d = d + 1.0;

    }

    return maxDeno;


  }

  private static long compQuad(int a, int b, int n){

    return Math.abs(n * n + a * n + b);

  }

  public static long quadraticPrimes(){

    int a = -999;
    int b;
    boolean isSeqValid = true;
    int maxA = a;
    int maxB = -999;

    int currentCount;
    int maxCount = 0;

    int n;

    for (;a < 1000; a++){

      for(b = -999;b < 1000; b++){
        //System.out.println("a:" + a + " b: " + b);
        n = 0;
        currentCount = 0;
        isSeqValid = true;
        while(isSeqValid){

          long result = compQuad(a,b,n);

          if(isPrime(result)){

            currentCount++;
            n++;

          }
          else{
            isSeqValid = false;
          }
        }

        //System.out.println("currentCount: " + currentCount);

        if(currentCount > maxCount){
          maxA = a;
          maxB= b;
          maxCount = currentCount;

          //System.out.println("maxA: " + maxA + " maxB: " + maxB + " maxCount: " + maxCount);
        }


      }

    }

    return maxA * maxB;

  }

  public static long spiralDiagonalSum(long [][] grid){

    int num = 1;

    int sumDiagonal = 0;

    grid[grid.length / 2][grid.length / 2] = 1;

    int rowIdx = grid.length / 2;
    int colIndex = grid.length / 2;

    int verticalMove = 0;
    int horizontalMove = 1;

    int rightLim = colIndex + 1;
    int downLim = colIndex + 1;
    int leftLim = grid.length/2 - 1;
    int upLim = grid.length/2 -1;

    while(colIndex < grid.length ){

      grid[rowIdx][colIndex] = num;
      num++;

      if(colIndex == rightLim){
        horizontalMove = 0;
        verticalMove = 1;
        rightLim++;

      }

      if(rowIdx == downLim){
        horizontalMove = -1;
        verticalMove = 0;
        downLim++;
      }

      if(colIndex == leftLim){
        horizontalMove = 0;
        verticalMove = -1;
        leftLim--;
      }

      if(rowIdx == upLim){
        horizontalMove = 1;
        verticalMove = 0;
        upLim--;
      }

      colIndex += horizontalMove;
      rowIdx += verticalMove;

    }

    for(int i = 0; i < grid.length; i++){
      if(i != grid.length / 2){

        sumDiagonal += grid[i][i];
      }
    }

    for(int j=grid.length-1; j >= 0; j--){
      sumDiagonal += grid[j][grid.length-j-1];
    }

    return sumDiagonal;

  }

  public static long distinctPowers(){
    Set<BigInteger> powers = new HashSet<BigInteger>();

    for (int a = 2; a <= 100; a++){

      for(int b = 2; b <= 100; b++){

        BigInteger bigBase = BigInteger.valueOf(a);

        BigInteger power = bigBase.pow(b);

        powers.add(power);

      }

    }

    return powers.size();
  }

  private static long sumOf5Powers(long num){

    long sum = 0;

    while(num > 0){
      int digit = (int) (num % 10);
      sum += Math.pow(digit, 5);
      num /= 10;
    }

    return sum;

  }

  public static long digitFifthPowers(){

    long limit = 1000000;

    long totalSum = 0;

    for (long num = 2; num < limit; num++){
      if(sumOf5Powers(num) == num){
        totalSum += num;
      }
    }

    return totalSum;


  }

  /**
   * last index of quantities, the number of ways we can achieve the sum
   * @param coins
   * @param quantities
   * @param sum
   * @param coinNum
   */
  public static void coinSums(int [] coins, int [] quantities, int sum, int coinNum, int targetCoin){

    if(coinNum == coins.length){
      if(sum == targetCoin){
        quantities[quantities.length-1]++;

      }
      return;
    }

    for (int q = 0; q <= quantities[coinNum]; q++){

      sum += q * coins[coinNum];

      if(sum == targetCoin){
        quantities[quantities.length-1]++;
        break;
      }

      if(sum < targetCoin){

        coinSums(coins, quantities, sum, coinNum +1, targetCoin);
        sum -= q * coins[coinNum];
      }
      else{
        break;
      }


    }

  }

  private static boolean hasUniqueDigits(int i){

    String str = Integer.toString(i);
    int checker = 0;
    for(int j=0; j<str.length();j++){
      int val = str.charAt(j) - '0';
      if((checker & (1 << val)) > 0){
        return false;
      }
      checker |= (1 << val);
    }

    return true;

  }

  private static void fillMuls(ArrayList<Integer> muls){

    for(int i=1; i < 10000; i++){
      if(hasUniqueDigits(i)){
        muls.add(i);
      }
    }

  }

  public static long pandigitalProductsSum(){

    HashSet<Integer> prevProducts = new HashSet<Integer>();
    long sum = 0;
    ArrayList<Integer> muls = new ArrayList<Integer>();
    String identity;
    fillMuls(muls);

    for(int i = 0; i < muls.size(); i++){

      for(int j = i+1; j < muls.size(); j++){

        identity = Integer.toString(muls.get(i)) + Integer.toString(muls.get(j));
        if(identity.length() <= 5){
          int product = muls.get(i) * muls.get(j);
          identity += Integer.toString(product);
          if(identity.length() == 9 && !identity.contains("0")){

            if(hasUniqueDigits(Integer.parseInt(identity))){

              if(!prevProducts.contains(product)){
                prevProducts.add(product);
                sum += product;
              }
            }
          }
        }

      }
    }
    return sum;
  }

  private static boolean isGoodFraction(double up, double down){

    double upTens =  Math.floor(up/10.0);
    double upUnit =  up%10.0;

    double downTens =  Math.floor(down/10.0);
    double downUnit =  down%10.0;

    double reduction = 0.0;

    if(upTens == downTens){
      reduction = upUnit/downUnit;
    }
    if(upTens == downUnit){
      reduction = upUnit/downTens;
    }
    if(upUnit == downUnit){
      reduction = upTens/downTens;
    }
    if(upUnit == downTens){
      reduction = upTens/downUnit;
    }

    if(reduction == up/down){
      return true;
    }
    return false;

  }

  public static double digCancelingFractions(){


    double productUp = 1.0;
    double productDown = 1.0;

    double up =10;
    double down;
    for(; up <= 98.0; up++){
      for(down = up+1; down<= 99;down++){
        if(up%10 != 0 && down %10 != 0){
          if(isGoodFraction(up,down)){
            productUp *= up;
            productDown *= down;
          }
        }
      }
    }
    return productUp / productDown;
  }

  private static int fac(int n){
    int mul =1;
    while(n > 1){
      mul *= n;
      n--;
    }

    return mul;
  }

  private static boolean curiousFactorial(int num){
    int tempNum = num;
    int sumOfFacDigits = 0;
    while(tempNum > 0){

      int dig = tempNum %10;
      sumOfFacDigits += fac(dig);
      tempNum /= 10;
    }

    if(sumOfFacDigits == num){
      return true;
    }
    return false;
  }

  public static long digitFactorials(int lim){

    long sum = 0;
    for(int i =3; i <= lim; i++){
      if(curiousFactorial(i)){
        sum+=i;
      }
    }
    return sum;
  }

  private static boolean isCircularPrime(int num){
    int numCirc;

    int numOfDigits = (int) Math.ceil(Math.log10(num));

    for(int i = 1; i < numOfDigits; i++){

      numCirc = num % (int)Math.pow(10, numOfDigits-1);
      num = num / (int)Math.pow(10, numOfDigits-1);
      numCirc *= 10;
      numCirc += num;

      if(!isPrime(numCirc)){
        return false;
      }
      num = numCirc;

    }
    return true;

  }

  public static int circularPrimes(){

    int counter = 13;

    int candidate = 100;

    while(candidate < 1000000){
      if(isPrime(candidate)){
        if(isCircularPrime(candidate)){
          counter++;
        }

      }
      candidate++;
    }
    return counter;

  }

  public static long doubleBasePal(){
    long sum = 0;

    int candidate = 1;
    while(candidate < 1000000){
      if(isPalindrom(candidate)){
        int revNum = Integer.reverse(candidate);
        int trails = Long.numberOfTrailingZeros(revNum);

        String revStrZeroes = Integer.toBinaryString(revNum);
        String revStr = revStrZeroes.substring(0,revStrZeroes.length() - trails);
        String orginalNum = Integer.toBinaryString(candidate);

//				System.out.println("revStr: " + revStr + " orginalNum: " + orginalNum);
//				System.out.println("candidate: " + candidate);
//				System.out.println(revStr.equals(orginalNum));
        if(revStr.equals(orginalNum)){
          sum += candidate;
        }

      }
      candidate++;
    }


    return sum;
  }

  private static boolean isTruncatable(long candidate){

    //check if trunctable from left to right
    int numOfDigits = (int) Math.floor(Math.log10(candidate));
    long modDivider = (long) Math.pow(10, numOfDigits);

    long mutatingCand = candidate;
    while(mutatingCand > 0){

      if(!isPrime(mutatingCand)){
        return false;
      }
      mutatingCand = mutatingCand % modDivider;
      modDivider = modDivider / 10;

    }

    //check if truncatable from right to left
    mutatingCand = candidate;
    while(mutatingCand > 0){

      if(!isPrime(mutatingCand)){
        return false;
      }
      mutatingCand = mutatingCand /10;
    }


    return true;
  }

  /**
   * Project Euler - problem 37, Truncatable primes
   */
  public static long sumOfTruncatablePrimes(){

    long finalSum = 0;

    long candidate = 11;
    int counter = 0;

    while(counter < 11){

      if(isPrime(candidate)){

        if(isTruncatable(candidate)){
          counter++;
          finalSum += candidate;
        }
      }
      candidate += 2;
    }
    return finalSum;
  }

  private static long getPanResult(long current){
    int multiply = 1;
    String currentStr = "";
    Set<Character> used = new HashSet<Character>();

    String compStr = "";

    while(currentStr.length() < 9){

      compStr += current * multiply;

      for(int j = 0; j < compStr.length(); j++){
        char currentChar = compStr.charAt(j);
        if(used.contains(currentChar) || currentChar == '0'){

          return 0;
        }
        else{
          //System.out.println(currentChar);
          used.add(currentChar);
        }

      }

      currentStr += compStr;
      compStr = "";
      System.out.println(currentStr);
      multiply++;

    }
    //System.out.println(currentStr);
    return Long.parseLong(currentStr);
  }

  public static long pandigitalMultiples(){

    long maxPanDigital = 123456789;
    long currentInteger = 2;

    while(currentInteger < 500000){
      long panResult = getPanResult(currentInteger);
      //System.out.println(panResult);
      if(panResult > maxPanDigital){
        maxPanDigital = panResult;
      }
      currentInteger++;
    }


    return maxPanDigital;
  }


  private static int getNumberOfSolutions(int p) {

    int c;
    int b;
    int a;

    int counter = 0;

    for(a= 3; a < p; a++){

      for (b = a ; b < p; b++){

        int squareC = a * a + b * b;
        double doubleC = Math.sqrt(squareC);
        c = (int) Math.floor(doubleC);

        if(c == doubleC && a != b && b < c && a + b + c == p){
          counter++;
        }

      }
    }

    return counter;
  }

  public static int integerRightTriangles(int max){

    int maxNumOfSolutions = 0;
    int maxP = 0;

    int numberOfSol;

    for(int p =3; p <= max ;p++){

      numberOfSol = getNumberOfSolutions(p);
      System.out.println("p: " + p + ", num of solutions:" + numberOfSol);
      if(numberOfSol > maxNumOfSolutions){
        maxNumOfSolutions = numberOfSol;
        maxP = p;
      }

    }

    return maxP;
  }




  private static void fillTriangles(HashSet<Integer> triangles){

    int currentTriangle = 1;

    int nextNatural = 2;

    while(currentTriangle <= 495){

      triangles.add(currentTriangle);
      currentTriangle += nextNatural;
      nextNatural++;
    }
  }

  public static int codedTriangleNumber(String fileName) throws IOException {

    int counter = 0;

    HashSet<Integer> triangles = new HashSet<Integer>();

    fillTriangles(triangles);

    Reader reader = new FileReader(fileName);
    BufferedReader br  = new BufferedReader(reader);

    String wordsRep = br.readLine();

    String [] words = wordsRep.split(",");

    for(int i = 0; i < words.length; i++){

      String word = words[i].substring(1, words[i].length() - 1);

      int wordValue = 0;
      for(int j = 0; j < word.length(); j++){

        char ch = word.charAt(j);
        wordValue += (ch - 64);


      }
      if(triangles.contains(wordValue)){
        counter++;
      }


    }
    br.close();

    return counter;

  }


  public static long ChampernownesConstant(int power){

    long result = 1;
    int currentNumber = 1;
    int digitCounter = 0;
    int numOfDigits = 0;

    int currPower = 0;

    while(currPower <= power){

      int temp = currentNumber;

      int totalNumDigits = (int) Math.log10(temp) + 1;
      while(totalNumDigits > 0){

        if(temp == 0){
          numOfDigits = 0;
        }
        else{
          numOfDigits = (int) Math.log10(temp);
        }

        int msd = temp / (int) Math.pow(10, numOfDigits);
        temp = temp % (int) Math.pow(10, numOfDigits);
        digitCounter ++;

        if(digitCounter == Math.pow(10, currPower)){

          result *= msd;
          currPower++;
        }
        totalNumDigits--;

      }
      currentNumber++;

    }

    return result;
  }

  public static long panDigitalPrime(){

    int maxPrime = 2143;

    for(int i = 2143; i < 987654321; i = i + 2 ){

      int candidate = i;

      int dic = 0; //our dictionary for the specific number (bitwise)

      int numOfDigits = (int) Math.ceil( Math.log10(candidate) );

      boolean isInnerBreak = false;

      while(candidate > 0){

        int digit = candidate % 10;
        candidate /= 10;

        if(digit == 0){
          isInnerBreak = true;
          break;
        }

        int bit = 1 << (digit - 1);

        if((dic & bit) == bit){ //if this digit was used before
          isInnerBreak = true;
          break;
        }

        dic += bit; //mark the digit as taken
      }
      if(isInnerBreak){
        continue;
      }

      //if this number is prime and greater than our max and all the digits
      //were used exactly once (checked by using gaps in the dictionary)

      if( (dic ^ ((1 << numOfDigits) - 1)) == 0 && i > maxPrime && isPrime(i)){

        maxPrime = i;

      }

    }

    return maxPrime;

  }

  private static boolean isOwnerOfDivProperty(long candidate){

    int d1, d2, d3, d4, d5, d6, d7, d8, d9, d10;

    d10 = (int) (candidate % 10); candidate /= 10;
    d9 = (int) (candidate % 10); candidate /= 10;
    d8 = (int) (candidate % 10); candidate /= 10;
    d7 = (int) (candidate % 10); candidate /= 10;
    d6 = (int) (candidate % 10); candidate /= 10;
    d5 = (int) (candidate % 10); candidate /= 10;
    d4 = (int) (candidate % 10); candidate /= 10;
    d3 = (int) (candidate % 10); candidate /= 10;
    d2 = (int) (candidate % 10); candidate /= 10;
    d1 = (int) (candidate % 10);

    boolean hasProp = true;

    int mul2 = d2 * 100 + d3 * 10 + d4;
    hasProp = hasProp && (mul2 % 2 == 0);
    if(!hasProp){
      return false;
    }

    int mul3 = d3 * 100 + d4 * 10 + d5;
    hasProp = hasProp && (mul3 % 3 == 0);
    if(!hasProp){
      return false;
    }

    int mul5 = d4 * 100 + d5 * 10 + d6;
    hasProp = hasProp && (mul5 % 5 == 0);
    if(!hasProp){
      return false;
    }

    int mul7 = d5 * 100 + d6 * 10 + d7;
    hasProp = hasProp && (mul7 % 7 == 0);
    if(!hasProp){
      return false;
    }

    int mul11 = d6 * 100 + d7 * 10 + d8;
    hasProp = hasProp && (mul11 % 11 == 0);
    if(!hasProp){
      return false;
    }

    int mul13 = d7 * 100 + d8 * 10 + d9;
    hasProp = hasProp && (mul13 % 13 == 0);
    if(!hasProp){
      return false;
    }

    int mul17 = d8 * 100 + d9 * 10 + d10;
    hasProp = hasProp && (mul17 % 17 == 0);
    if(!hasProp){
      return false;
    }

    return true;

  }

  public static long subStringDivisibility(){

    long sum =0;

    for(long candidate = 1023456789; candidate  <= 9876543210L; candidate++){

      long candidateCopy = candidate;

      int dic = 0; //our dictionary for the specific number (bitwise)

      boolean isInnerBreak = false;

      while(candidateCopy > 0){

        int digit = (int) (candidateCopy % 10);
        candidateCopy /= 10;

        int bit = 1 << digit;

        if((dic & bit) == bit){ //if this digit was used before
          isInnerBreak = true;
          break;
        }

        dic += bit; //mark the digit as taken

      }
      if(isInnerBreak){
        continue;
      }
      System.out.println(candidate);

      if( (dic ^ ((1 << 10) - 1)) == 0 && isOwnerOfDivProperty(candidate)){

        sum += candidate;
      }

    }
    return sum;
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }
}
