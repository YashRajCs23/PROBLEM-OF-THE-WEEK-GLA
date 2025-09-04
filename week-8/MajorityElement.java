import java.util.HashMap;
import java.util.Map;

public class MajorityElement{
    //brute force approach : Scans the array and counts the occurrences of each element
    //time complexity: O(n^2)
    //space complexity: O(1)
    public int majorityElement(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++){
            int count = 0;
            for(int j=0; j<n; j++){
                if(nums[i] == nums[j]){
                    count++;
                }
            }
            if(count > n/2){
                return nums[i];
            }
        }
        return -1; // This line will never be reached if input is valid
    }
    //Better approach: Using HashMap to count occurrences
    //time complexity: O(n)
    //space complexity: O(n)
    public int majorityElementBetter(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int n = nums.length;
        for(int num : nums){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if(countMap.get(num) > n/2){
                return num;
            }
        }
        return -1; // This line will never be reached if input is valid
    }
    //Optimal approach: Boyer-Moore Voting Algorithm
    //time complexity: O(n)
    //space complexity: O(1)
    public int majorityElementOptimal(int[] nums) {
        int count=0;
        int el=0;
        for(int i=0;i<nums.length;i++){
            if(count==0){
                count=1;
                el=nums[i];
            }
            else if(nums[i]==el){
                count++;
            }
            else{
                count--;
            }
        }
        int c=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==el) c++;
        }
        if(c>(nums.length)/2) return el;
        else return -1;
    }
}