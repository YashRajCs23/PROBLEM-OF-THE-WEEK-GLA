import java.util.HashMap;
public class subArraySumEqualsK {
    // Brute-force approach with 3 loops
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int l = i; l <= j; l++) {
                    sum += nums[l];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    //TC-O(N^3) 

    //Better - 2loops
    public int subarraySumBetter(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    // TC-O(N^2)

    //optimal - hashmap
    //We use a running prefix sum and a HashMap to store how many times a prefix sum has occurred.
    //If prefixSum - k exists in the map, it means there's a subarray that sums to k.
    public int subarraySumOptimal(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
    // TC-O(N) , SC-O(N)
}