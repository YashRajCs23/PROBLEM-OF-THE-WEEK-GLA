public class LCSofThreeStrings {
    // Brute -> Recursive Approach
    // TC: O(2^(m+n+k)), SC: O(m+n+k)
    public static int LCSRecursive(String A, String B, String C, int m, int n, int k) {
        if (m == 0 || n == 0 || k == 0) return 0;
        if (A.charAt(m - 1) == B.charAt(n - 1) && A.charAt(m - 1) == C.charAt(k - 1)) {
            return 1 + LCSRecursive(A, B, C, m - 1, n - 1, k - 1);
        }
        return Math.max(
            Math.max(LCSRecursive(A, B, C, m - 1, n, k), LCSRecursive(A, B, C, m, n - 1, k)),
            LCSRecursive(A, B, C, m, n, k - 1)
        );
    }

    // Memoization (Top-down DP)
    // TC: O(m*n*k), SC: O(m*n*k)
    public static int LCSMemo(String A, String B, String C) {
        int m = A.length(), n = B.length(), k = C.length();
        int[][][] dp = new int[m + 1][n + 1][k + 1];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= n; j++)
                for (int l = 0; l <= k; l++)
                    dp[i][j][l] = -1;
        return findLCSMemo(A, B, C, m, n, k, dp);
    }

    private static int findLCSMemo(String A, String B, String C, int m, int n, int k, int[][][] dp) {
        if (m == 0 || n == 0 || k == 0) return 0;
        if (dp[m][n][k] != -1) return dp[m][n][k];
        if (A.charAt(m - 1) == B.charAt(n - 1) && A.charAt(m - 1) == C.charAt(k - 1)) {
            dp[m][n][k] = 1 + findLCSMemo(A, B, C, m - 1, n - 1, k - 1, dp);
        } else {
            dp[m][n][k] = Math.max(
                Math.max(findLCSMemo(A, B, C, m - 1, n, k, dp), findLCSMemo(A, B, C, m, n - 1, k, dp)),
                findLCSMemo(A, B, C, m, n, k - 1, dp)
            );
        }
        return dp[m][n][k];
    }

    // Tabulation - Bottom-up DP
    // TC: O(m*n*k), SC: O(m*n*k)
    public static int LCSTabulation(String A, String B, String C) {
        int m = A.length(), n = B.length(), k = C.length();
        int[][][] dp = new int[m + 1][n + 1][k + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t <= k; t++) {
                    if (i == 0 || j == 0 || t == 0) {
                        dp[i][j][t] = 0;
                    } else if (A.charAt(i - 1) == B.charAt(j - 1) && A.charAt(i - 1) == C.charAt(t - 1)) {
                        dp[i][j][t] = 1 + dp[i - 1][j - 1][t - 1];
                    } else {
                        dp[i][j][t] = Math.max(
                            Math.max(dp[i - 1][j][t], dp[i][j - 1][t]),
                            dp[i][j][t - 1]
                        );
                    }
                }
            }
        }
        return dp[m][n][k];
    }

    // Space-Optimized DP
    // TC: O(m*n*k), SC: O(2*n*k)
    public static int LCSSpaceOptimized(String A, String B, String C) {
        int m = A.length(), n = B.length(), k = C.length();
        int[][] prev = new int[n + 1][k + 1];
        int[][] curr = new int[n + 1][k + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int t = 1; t <= k; t++) {
                    if (A.charAt(i - 1) == B.charAt(j - 1) && A.charAt(i - 1) == C.charAt(t - 1)) {
                        curr[j][t] = 1 + prev[j - 1][t - 1];
                    } else {
                        curr[j][t] = Math.max(
                            Math.max(prev[j][t], curr[j - 1][t]),
                            curr[j][t - 1]
                        );
                    }
                }
            }
            for (int j = 0; j <= n; j++) {
                System.arraycopy(curr[j], 0, prev[j], 0, k + 1);
            }
        }
        return prev[n][k];
    }
}