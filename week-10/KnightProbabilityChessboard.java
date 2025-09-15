/*
 * Knight Probability in Chessboard
 *
 * Approach:
 * ---------
 * Top-down DP (memoized recursion).
 * From each cell, the knight has 8 possible moves.
 * Base cases:
 *   • Out of board → probability 0.
 *   • k == 0 → probability 1.
 * Memoize results for state (k, row, col).
 *
 * Time Complexity:  O(K * n^2)
 * Space Complexity: O(K * n^2) for memo + O(K) recursion stack.
 */
import java.util.*;
public class KnightProbabilityChessboard {
    private Map<String, Double> memo = new HashMap<>();
    private final int[][] directions = {
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
        {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };
    private double helper(int n, int k, int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) return 0.0;
        if (k == 0) return 1.0;
        String key = k + "_" + row + "_" + col;
        if (memo.containsKey(key)) return memo.get(key);
        double ans = 0.0;
        for (int[] d : directions) {
            ans += helper(n, k - 1, row + d[0], col + d[1]);
        }
        double result = ans / 8.0;
        memo.put(key, result);
        return result;
    }
    public double knightProbability(int n, int k, int row, int column) {
        return helper(n, k, row, column);
    }
}