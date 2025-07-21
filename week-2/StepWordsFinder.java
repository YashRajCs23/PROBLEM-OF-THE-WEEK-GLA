import java.util.*;
public class StepWordsFinder {
    // Brute Force -> using sorting and deletion
    // TC: O(N*K^2*logK), SC: O(1)
    public static List<String> stepWordsBrute(String word, List<String> dict) {
        List<String> result = new ArrayList<>();
        char[] base = word.toCharArray();
        Arrays.sort(base);
        int k = word.length();
        for (String d : dict) {
            if (d.length() != k + 1) continue;
            char[] temp = d.toCharArray();
            Arrays.sort(temp);
            for (int i = 0; i < temp.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < temp.length; j++) {
                    if (i != j) sb.append(temp[j]);
                }
                if (sb.toString().equals(String.valueOf(base))) {
                    result.add(d);
                    break;
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    // Better -> Count Array
    // TC: O(N*26), SC: O(26)
    public static List<String> stepWordsFrequency(String word, List<String> dict) {
        List<String> result = new ArrayList<>();
        int[] baseFreq = new int[26];
        for (char c : word.toCharArray()) baseFreq[c - 'a']++;
        int k = word.length();
        for (String d : dict) {
            if (d.length() != k + 1) continue;
            int[] freq = new int[26];
            for (char c : d.toCharArray()) freq[c - 'a']++;
            int diff = 0;
            boolean valid = true;
            for (int i = 0; i < 26; i++) {
                if (freq[i] < baseFreq[i]) {
                    valid = false;
                    break;
                }
                diff += freq[i] - baseFreq[i];
            }
            if (valid && diff == 1) result.add(d);
        }
        Collections.sort(result);
        return result;
    }

    // Optimal -> HashMap Frequency
    // TC: O(N*K), SC: O(K)
    public static List<String> stepWordsHashMap(String word, List<String> dict) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> baseMap = new HashMap<>();
        for (char c : word.toCharArray())
            baseMap.put(c, baseMap.getOrDefault(c, 0) + 1);
        int k = word.length();
        for (String d : dict) {
            if (d.length() != k + 1) continue;
            Map<Character, Integer> map = new HashMap<>();
            for (char c : d.toCharArray())
                map.put(c, map.getOrDefault(c, 0) + 1);
            boolean valid = true;
            int diff = 0;
            for (char c : map.keySet()) {
                int dictCount = map.get(c);
                int baseCount = baseMap.getOrDefault(c, 0);
                if (dictCount < baseCount) {
                    valid = false;
                    break;
                }
                diff += dictCount - baseCount;
            }
            if (valid && diff == 1) result.add(d);
        }
        Collections.sort(result);
        return result;
    }
}