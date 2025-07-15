;public class minSortedArray{
    //brute apporach -> Just find the minimum element in the array by checking each one
    public int findMin(int[] nums) {
        int min=nums[0];
        for(int i=1;i<nums.length;i++) {
            if(nums[i]<min) {
                min=nums[i];
            }
        }
        return min;
    }
    //Time Complexity:O(n) — Checks every element , Space Complexity:O(1) — No extra space used

    //optimal-> binary search
    // Check if left half is sorted (nums[low] <= nums[mid])
    // If yes, then the minimum is either nums[low] or in the right half.
    // Else, the right half is unsorted, so:
    // The minimum is in the left half, possibly at mid.
    // Update the result with the minimum seen so far using Math.min.
    // Adjust low and high accordingly to keep searching in the unsorted part.
    public int findMinOptimal(int[] nums) {
        int low=0,high=nums.length-1,ans=Integer.MAX_VALUE;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[low]<=nums[mid]){
                ans=Math.min(ans,nums[low]);
                low=mid+1;
            }else{
                ans=Math.min(ans,nums[mid]);
                high=mid-1;
            }
        }
        return ans;
    }
    //Time: O(log n) — Binary search halves the range each time , Space: O(1) — No extra space used
}