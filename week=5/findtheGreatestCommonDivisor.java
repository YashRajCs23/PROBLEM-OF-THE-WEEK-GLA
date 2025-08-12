public class findtheGreatestCommonDivisor {
    // 1. Find the minimum and maximum values in the array.
    // 2. The GCD of the entire array will be the same as the GCD of min and max.
    // 3. Use Euclidean Algorithm to find the GCD.
    // Time Complexity: O(n + log(min, max))
    //   - O(n) to find min and max in the array.
    //   - O(log(min, max)) for the GCD calculation.
    // Space Complexity: O(1) → Constant extra space.
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return gcd(min, max);
    }
    // Euclidean Algorithm
    public int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
}