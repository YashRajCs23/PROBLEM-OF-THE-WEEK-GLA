import java.util.*;
public class SmallestNonRepresentableSum {
    // Approach 1: Brute Force using recursion
    // TC: O(2^n), SC: O(2^n)
    static void generateSums(int[] arr, int i, int sum, Set<Integer> sums) {
        if (i == arr.length) {
            sums.add(sum);
            return;
        }
        generateSums(arr, i + 1, sum, sums);
        generateSums(arr, i + 1, sum + arr[i], sums);
    }

    public static int smallestMissingBrute(int[] arr) {
        Set<Integer> sums = new HashSet<>();
        generateSums(arr, 0, 0, sums);
        int target = 1;
        while (sums.contains(target)) target++;
        return target;
    }

    // Approach 2: DP (Subset Sum Table)
    // TC: O(n * sum), SC: O(sum)
    public static int smallestMissingDP(int[] arr) {
        int maxSum = Arrays.stream(arr).sum();
        boolean[] dp = new boolean[maxSum + 2];
        dp[0] = true;
        for (int num : arr) {
            for (int i = maxSum; i >= 0; i--) {
                if (dp[i]) dp[i + num] = true;
            }
        }
        for (int i = 1; i <= maxSum + 1; i++) {
            if (!dp[i]) return i;
        }
        return maxSum + 1;
    }

    // Approach 3: Greedy (Efficient)
    // TC: O(n log n), SC: O(1)
    public static int smallestMissingGreedy(int[] arr) {
        Arrays.sort(arr);
        int res = 1;
        for (int num : arr) {
            if (num > res) break;
            res += num;
        }
        return res;
    }
}