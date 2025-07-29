public class wordSearchinTwoDMatrix {
    // Brute-force DFS approach for single word using backtracking
    // TC: O(N * M * 4^L), SC: O(L) where N = rows, M = cols, L = word length
    public boolean existDFS(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        if (idx == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(idx)) return false;
        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = dfs(board, i + 1, j, word, idx + 1) || dfs(board, i - 1, j, word, idx + 1) || dfs(board, i, j + 1, word, idx + 1) || dfs(board, i, j - 1, word, idx + 1);
        board[i][j] = temp;
        return found;
    }
}