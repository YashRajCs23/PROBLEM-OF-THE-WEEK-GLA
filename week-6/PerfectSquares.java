import java.util.*;
public class PerfectSquares {
    // 1. Recursive (Brute Force) - Exponential
    private int solveRec(int n) {
        if (n == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, 1 + solveRec(n - i * i));
        }
        return ans;
    }
    public int numSquaresRecursive(int n) {
        return solveRec(n);
    }

    // 2. Memoization (Top-Down) - O(n * sqrt(n)), O(n) space
    private int solveMemo(int n, int[] dp) {
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, 1 + solveMemo(n - i * i, dp));
        }
        return dp[n] = ans;
    }
    public int numSquaresMemo(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solveMemo(n, dp);
    }

    // 3. Tabulation (Bottom-Up) - O(n * sqrt(n)), O(n) space
    public int numSquaresTabu(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        return dp[n];
    }
}