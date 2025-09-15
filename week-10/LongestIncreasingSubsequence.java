import java.util.*;
public class LongestIncreasingSubsequence {
    // ---------- Method 1: Pure Recursion ----------
    // T.C: O(2^n), each element can be taken or skipped
    // S.C: O(n), recursion stack
    public int lengthOfLISRecursive(int[] nums) {
        return helper(nums, 0, Integer.MIN_VALUE);
    }

    private int helper(int[] nums, int i, int prev) {
        if (i == nums.length) return 0;
        int skip = helper(nums, i + 1, prev);
        int take = 0;
        if (nums[i] > prev) take = 1 + helper(nums, i + 1, nums[i]);
        return Math.max(skip, take);
    }

    // ---------- Method 3: Recursion + 2D Memoization ----------
    // T.C: O(n^2), each (i, prevIdx) pair is computed once
    // S.C: O(n^2) dp array + O(n) recursion stack
    private int[][] dp;

    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        dp = new int[n][n + 1];
        for (int r = 0; r < n; r++) Arrays.fill(dp[r], -1);
        return solve(nums, 0, -1);
    }

    private int solve(int[] nums, int i, int prevIdx) {
        if (i == nums.length) return 0;
        if (dp[i][prevIdx + 1] != -1) return dp[i][prevIdx + 1];

        int skip = solve(nums, i + 1, prevIdx);
        int take = 0;
        if (prevIdx == -1 || nums[i] > nums[prevIdx]) take = 1 + solve(nums, i + 1, i);

        return dp[i][prevIdx + 1] = Math.max(take, skip);
    }

    // ---------- Method 4: Bottom-Up DP (O(n^2)) ----------
    // T.C: O(n^2), nested loops
    // S.C: O(n), dp array
    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // ---------- Method 5: Binary Search / Patience Sorting (O(n log n)) ----------
    // T.C: O(n log n), binary search for each element
    // S.C: O(n), the list stores elements of LIS
    public int lengthOfLISBinarySearch(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < num) {
                lis.add(num);
            } else {
                int idx = lowerBound(lis, num);
                lis.set(idx, num);
            }
        }
        return lis.size();
    }

    private int lowerBound(List<Integer> list, int target) {
        int start = 0, end = list.size() - 1, ans = list.size();
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) >= target) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
}
