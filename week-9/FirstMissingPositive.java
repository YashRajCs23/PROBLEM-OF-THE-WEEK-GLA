public class FirstMissingPositive {
    /*
     * Approach 1: Negative Marking
     * - Replace invalid values with 1.
     * - Use index positions and mark visited numbers by negating values.
     * - First index with positive value => missing number.
     * TC: O(n), SC: O(1)
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean contains1 = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) contains1 = true;
            if (nums[i] <= 0 || nums[i] > n) nums[i] = 1;
        }
        if (!contains1) return 1;
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;
            if (nums[idx] > 0) nums[idx] *= -1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }
    /*
     * Approach 2: Cyclic Sort
     * - Place each number at its correct index: number x -> index x-1.
     * - Swap until numbers are in correct positions.
     * - First mismatch index => missing number.
     * TC: O(n), SC: O(1)
     */
    public int firstMissingPositiveCycle(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return n + 1;
    }
}