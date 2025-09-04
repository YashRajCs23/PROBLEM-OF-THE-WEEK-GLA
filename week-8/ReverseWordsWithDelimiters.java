import java.util.*;
public class ReverseWordsWithDelimiters {
    // 1. Split + Reconstruct
    // TC: O(n)  -> single pass to split + single pass to rebuild
    // SC: O(n)  -> extra storage for tokens + words list
    public static String reverseWordsSplit(String s) {
        List<String> words = new ArrayList<>();
        List<String> tokens = new ArrayList<>();       
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    tokens.add(sb.toString()); // word
                    words.add(sb.toString());
                    sb.setLength(0);
                }
                tokens.add(String.valueOf(c)); // delimiter
            }
        }
        if (sb.length() > 0) {
            tokens.add(sb.toString());
            words.add(sb.toString());
        }
        Collections.reverse(words);
        int wi = 0;
        StringBuilder result = new StringBuilder();
        for (String t : tokens) {
            if (Character.isLetter(t.charAt(0))) {
                result.append(words.get(wi++));
            } else {
                result.append(t);
            }
        }
        return result.toString();
    }
    // 2. Two-Pointer Approach
    // TC: O(n)  -> one pass to collect words + one pass to rebuild
    // SC: O(n)  -> stores list of words
    public static String reverseWordsTwoPointer(String s) {
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    words.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        if (sb.length() > 0) words.add(sb.toString());   
        Collections.reverse(words);
        int wi = 0;
        StringBuilder result = new StringBuilder();
        sb.setLength(0);
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    result.append(words.get(wi++));
                    sb.setLength(0);
                }
                result.append(c);
            }
        }
        if (sb.length() > 0) result.append(words.get(wi++));
        return result.toString();
    }

    // 3. Stack Approach
    // TC: O(n)  -> one pass to push + one pass to rebuild
    // SC: O(n)  -> stack stores words
    public static String reverseWordsStack(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();   
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        if (sb.length() > 0) stack.push(sb.toString());  
        sb.setLength(0);
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                if (sb.length() == 0) {
                    result.append(stack.pop());
                }
                sb.append(c);
            } else {
                sb.setLength(0);
                result.append(c);
            }
        }
        return result.toString();
    }
}