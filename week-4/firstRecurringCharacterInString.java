import java.util.HashSet;
import java.util.Set;
public class firstRecurringCharacterInString {
    // Brute Force Approach : Check each character with all previous characters to find the first repeating one.
    public static String firstRepChar(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return Character.toString(s.charAt(i));
                }
            }
        }
        return "-1";
    }
    // Time Complexity: O(n^2) - due to nested loops
    // Space Complexity: O(1) - no extra space used apart from constants


    // Optimized Approach:
    // Use a frequency array to track first repeating character in one pass.
    public static String firstRepChara(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int index = ch - 'a';
            if (charCount[index] != 0) {
                return Character.toString(ch);
            }
            charCount[index]++;
        }
        return "-1";
    }
    // Time Complexity: O(n) - single pass through the string
    // Space Complexity: O(1) - fixed-size array (26 elements)


    // Optimized Approach using HashSet:
    // Traverse string and track seen characters.
    public static String firstRep(String s) {
        Set<Character> repeat = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (repeat.contains(ch)) {
                return Character.toString(ch);
            }
            repeat.add(ch);
        }
        return "-1";
    }
    // Time Complexity: O(n) - one pass through the string
    // Space Complexity: O(n) - in worst case, all unique characters


    public static void main(String[] args) {
        String s = "abca";
        System.out.println("Brute Force: " + firstRepChar(s));
        System.out.println("Optimized with Frequency Array: " + firstRepChara(s));
        System.out.println("Optimized with HashSet: " + firstRep(s));
    }
}