public class rubySecondHouse {
    // Brute-force — Exponential time: O(k^n), works for small n only
    public static int minCostBrute(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        return helper(costs, n, k, 0, -1);
    }
    // Recursive helper for brute-force
    private static int helper(int[][] costs, int n, int k, int houseIndex, int prevColor) {
        if (houseIndex == n) return 0;
        int minCost = Integer.MAX_VALUE;
        for (int color = 0; color < k; color++) {
            if (color != prevColor) {
                int currCost = costs[houseIndex][color] + helper(costs, n, k, houseIndex + 1, color);
                minCost = Math.min(minCost, currCost);
            }
        }
        return minCost;
    }
    
    // Better — O(n × k²), standard DP approach
    public static int minCostBetter(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        // First house: copy costs
        for (int j = 0; j < k; j++) dp[0][j] = costs[0][j];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int c = 0; c < k; c++) {
                    if (c != j) dp[i][j] = Math.min(dp[i][j], dp[i - 1][c]);
                }
                dp[i][j] += costs[i][j];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) result = Math.min(result, dp[n - 1][j]);
        return result;
    }

    // Optimal — O(n × k), tracks min1 and min2 for each row
    public static int minCostOptimal(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        if (k == 0) return 0;
        int min1 = -1, min2 = -1;
        for (int i = 0; i < n; i++) {
            int lastMin1 = min1, lastMin2 = min2;
            min1 = -1;
            min2 = -1;
            for (int j = 0; j < k; j++) {
                if (i > 0) {
                    if (j != lastMin1) {
                        costs[i][j] += costs[i - 1][lastMin1];
                    } else {
                        costs[i][j] += costs[i - 1][lastMin2];
                    }
                }
                if (min1 == -1 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 == -1 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }
}