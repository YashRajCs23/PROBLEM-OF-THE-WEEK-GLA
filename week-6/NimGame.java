public class NimGame {
    /**
     * Approach:
     * - If n % 4 == 0, then no matter how many stones you remove (1,2,3),
     *   the opponent can always remove stones such that in each round
     *   a total of 4 stones are removed. Eventually, you will face 0 and lose.
     * - If n % 4 != 0, you can always force the opponent into a multiple of 4
     *   and secure a win.
     *
     * Time Complexity (TC): O(1) → Only one modulo operation is performed.
     * Space Complexity (SC): O(1) → No extra data structures used.
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0; // true if not divisible by 4, false otherwise
    }
}