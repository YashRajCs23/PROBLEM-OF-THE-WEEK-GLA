import java.util.*;
public class PrimeMultipleOccurrencesAll {
    // ---------------- BASIC APPROACH ----------------
    // TC: O(N * M)  where M = value of number
    // SC: O(N)
    private static boolean isPrimeBasic(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // TC: O(N * M), SC: O(N)
    public static List<Integer> findRepeatedPrimesBasic(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        Set<Integer> added = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            if (isPrimeBasic(num) && freq.get(num) > 1 && !added.contains(num)) {
                result.add(num);
                added.add(num);
            }
        }
        return result;
    }

    // ---------------- BRUTE FORCE ----------------
    // TC: O(N * sqrt(M)), SC: O(N)
    private static boolean isPrimeBrute(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // TC: O(N * sqrt(M)), SC: O(N)
    public static List<Integer> findRepeatedPrimesBrute(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        Set<Integer> added = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            if (isPrimeBrute(num) && freq.get(num) > 1 && !added.contains(num)) {
                result.add(num);
                added.add(num);
            }
        }
        return result;
    }

    // ---------------- MEMOIZED ----------------
    private static Map<Integer, Boolean> primeCache = new HashMap<>();

    // TC: O(sqrt(M)) per unique number, SC: O(U) [U = unique nums cached]
    private static boolean isPrimeMemo(int n) {
        if (primeCache.containsKey(n)) return primeCache.get(n);

        boolean result = true;
        if (n <= 1) result = false;
        else if (n == 2 || n == 3) result = true;
        else if (n % 2 == 0 || n % 3 == 0) result = false;
        else {
            for (int i = 5; i * i <= n; i += 6) {
                if (n % i == 0 || n % (i + 2) == 0) {
                    result = false;
                    break;
                }
            }
        }
        primeCache.put(n, result);
        return result;
    }

    // TC: O(N * sqrt(M)) but faster with caching, SC: O(N + U)
    public static List<Integer> findRepeatedPrimesMemo(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        Set<Integer> added = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            if (isPrimeMemo(num) && freq.get(num) > 1 && !added.contains(num)) {
                result.add(num);
                added.add(num);
            }
        }
        return result;
    }

    // ---------------- OPTIMAL (SIEVE) ----------------
    private static final int MAX = 1000000;
    private static boolean[] isPrime = new boolean[MAX + 1];

    // TC (sieve building): O(M log log M), SC: O(M)
    static {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    // TC: O(N), SC: O(N + M)
    public static List<Integer> findRepeatedPrimesOptimal(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        Set<Integer> added = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        for (int num : arr) {
            if (num > 1 && isPrime[num] && freq.get(num) > 1 && !added.contains(num)) {
                result.add(num);
                added.add(num);
            }
        }
        return result;
    }
}