public class BitwiseANDNumbersRange {
    /*
     * Approach 1: Using bit shifting
     * ---------------------------------
     * Keep right-shifting both left and right until they become equal.
     * Count the shifts and left-shift the final value back to its original position.
     *
     * Time Complexity : O(log n)
     * Space Complexity: O(1)
     */
    public int rangeBitwiseAndShift(int left, int right) {
        int shiftCount = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shiftCount++;
        }
        return left << shiftCount;
    }

    /*
     * Approach 2: Using AND property
     * ---------------------------------
     * Continuously clear the least significant set bit of 'right'
     * until right <= left.
     *
     * Time Complexity : O(log n)
     * Space Complexity: O(1)
     */
    public int rangeBitwiseAndAndProperty(int left, int right) {
        while (right > left) {
            right = right & (right - 1);
        }
        return right;
    }
}