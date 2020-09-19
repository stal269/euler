import java.util.ArrayList;
import java.util.List;

public class Util {

  public static List<Integer> eSieve(final int limit) {
    final boolean[] candidates = new boolean[limit];
    int p = 2;

    while (p <= limit) {
      int mul = 2;
      int currentMul = mul * p;

      while (currentMul < limit) {
        candidates[currentMul] = true;
        mul++;
        currentMul = mul * p;
      }

      do {
        p++;
      }
      while (p < limit && candidates[p]);
    }

    final List<Integer> primes = new ArrayList<>();

    for(int i = 2; i < candidates.length; i++) {
      if (!candidates[i]) {
        primes.add(i);
      }
    }

    return primes;
  }

}
