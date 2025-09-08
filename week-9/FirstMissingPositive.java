public class FirstMissingPositive{
    /*
     * Problem: Find the smallest missing positive integer.
     * Approach: 
     * - Use array indices as markers (hashing idea).
     * - Replace invalid values with 1.
     * - Mark presence of numbers by negating the value at corresponding index.
     * - First index with positive value => missing number.
     *
     * T.C : O(n)   (iterate array a few times)
     * S.C : O(1)   (in-place marking, no extra space)
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean contains1 = false;
        // Step 1: Replace invalid numbers and check for presence of '1'
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                contains1 = true;
            }
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1; // replace out-of-range numbers with 1
            }
        }
        if (!contains1) return 1; // if '1' not present, that's the answer
        // Step 2: Use index as a hash key and negative sign as presence detector
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;
            if (nums[idx] > 0) {
                nums[idx] *= -1; // mark presence
            }
        }
        // Step 3: First positive index indicates missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        // Step 4: If all numbers 1..n are present, return n+1
        return n + 1;
    }
}