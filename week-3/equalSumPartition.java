public class equalSumPartition {
    //Recvurssive aapproach : TC: O(2^n) SC: O(n)
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        return canPartitionUtil(nums, nums.length - 1, target);
    }
    private static boolean canPartitionUtil(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (index == 0) return nums[0] == target;
        boolean notPick = canPartitionUtil(nums, index - 1, target);
        boolean pick = false;
        if (nums[index] <= target) {
            pick = canPartitionUtil(nums, index - 1, target - nums[index]);
        }
        return pick || notPick;
    }

    //Memoization approach : TC: O(n*target) SC: O(n*target) + O(n)
    public static boolean canPartitionMemo(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        Boolean[][] dp = new Boolean[nums.length][target + 1];
        return canPartitionMemoUtil(nums, nums.length - 1, target, dp);
    }
    private static boolean canPartitionMemoUtil(int[] nums, int index, int target, Boolean[][] dp) {
        if (target == 0) return true;
        if (index == 0) return nums[0] == target;
        if (dp[index][target] != null) return dp[index][target];       
        boolean notPick = canPartitionMemoUtil(nums, index - 1, target, dp);
        boolean pick = false;
        if (nums[index] <= target) {
            pick = canPartitionMemoUtil(nums, index - 1, target - nums[index], dp);
        }     
        return dp[index][target] = pick || notPick;
    }

    //Tabulation approach : TC: O(n*target) SC: O(n*target)
    public static boolean canPartitionTab(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true; // If target is 0, we can always achieve it
        }
        if (nums[0] <= target) dp[0][nums[0]] = true; // First element case
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (nums[i] <= j) {
                    pick = dp[i - 1][j - nums[i]];
                }
                dp[i][j] = pick || notPick;
            }
        }
        return dp[nums.length - 1][target];
    }

    //Space Optimized approach : TC: O(n*target) SC: O(target)
    public static boolean canPartitionSpaceOpt(int[] nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return false;
        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // If target is 0, we can always achieve it
        if (nums[0] <= target) dp[nums[0]] = true; // First element case
        for (int i = 1; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}