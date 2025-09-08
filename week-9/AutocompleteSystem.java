public class AutocompleteSystem {
    // Node class represents each character node in the Trie
    class Node {
        Node[] links = new Node[26]; // Only lowercase 'a' - 'z'
        boolean flag; // Marks end of a word
        // Store a node reference for a character
        void put(char a, Node node) {
            links[a - 'a'] = node;
        }
        // Get node reference for a character
        Node get(char a) {
            return links[a - 'a'];
        }
        // Check if node exists for a character
        boolean containsKey(char a) {
            return links[a - 'a'] != null;
        }
        // Mark end of word
        void setEnd() {
            flag = true;
        }
        // Check if it is end of word
        boolean isEnd() {
            return flag;
        }
    }
    // Trie class to store and query words
    class Trie {
        private Node root;
        public Trie() {
            root = new Node();
        }
        /**
         * Insert a word into the Trie
         * TC: O(L) where L = length of word
         * SC: O(L) in worst case (if new nodes are created)
         */
        public void insert(String word) {
            Node temp = root;
            for (int i = 0; i < word.length(); i++) {
                if (!temp.containsKey(word.charAt(i))) {
                    temp.put(word.charAt(i), new Node());
                }
                temp = temp.get(word.charAt(i));
            }
            temp.setEnd(); // Mark end of word
        }
        /**
         * Search if a word exists in Trie
         * TC: O(L) where L = length of word
         * SC: O(1)
         */
        public boolean search(String word) {
            Node temp = root;
            for (int i = 0; i < word.length(); i++) {
                if (!temp.containsKey(word.charAt(i))) {
                    return false; // character not found
                }
                temp = temp.get(word.charAt(i));
            }
            return temp.isEnd(); // return true only if it's a complete word
        }
        /**
         * Check if any word starts with given prefix
         * TC: O(P) where P = length of prefix
         * SC: O(1)
         */
        public boolean startsWith(String prefix) {
            Node temp = root;
            for (int i = 0; i < prefix.length(); i++) {
                if (!temp.containsKey(prefix.charAt(i))) {
                    return false; // prefix doesn't exist
                }
                temp = temp.get(prefix.charAt(i));
            }
            return true; // prefix exists
        }
    }
}