class Solution {
    /*
    Basic Approach:
    1. Start from the given starting pixel (sr, sc).
    2. If the pixel's color matches the initial color, change it to the new color.
    3. Recursively apply the same process to all 4-connected neighbors (up, right, down, left).
    4. Stop when the boundary is reached or when the color doesn't match the initial color.
    Time Complexity:  O(N * M) 
    - In the worst case, every pixel in the grid is visited once.
    Space Complexity: O(N * M) 
    - Due to recursion stack space in DFS.
    */
    private void dfs(int row, int col, int[][] ans, int[][] image, int newColor, int[] delRow, int[] delCol, int iniColor) {
        ans[row][col] = newColor;
        int n = image.length;
        int m = image[0].length;    
        for (int i = 0; i < 4; i++) {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];
            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                image[nrow][ncol] == iniColor && ans[nrow][ncol] != newColor) {
                dfs(nrow, ncol, ans, image, newColor, delRow, delCol, iniColor);
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int iniColor = image[sr][sc];
        int[][] ans = image;
        int[] delRow = {-1, 0, +1, 0};
        int[] delCol = {0, +1, 0, -1};
        dfs(sr, sc, ans, image, newColor, delRow, delCol, iniColor);
        return ans;
    }
}