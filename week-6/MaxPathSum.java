public class MaxPathSum {
    class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
    }
    // ---------------- 1. Naive Recursive (Exponential, not used in practice) ----------------
    private int maxPathNaive(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        // Compute max path sum through this node
        int left = maxPathDown(root.left);
        int right = maxPathDown(root.right);
        int throughNode = root.val + Math.max(0, left) + Math.max(0, right);
        // Recurse left and right
        return Math.max(throughNode, Math.max(maxPathNaive(root.left), maxPathNaive(root.right)));
    }
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        return node.val + Math.max(0, Math.max(maxPathDown(node.left), maxPathDown(node.right)));
    }
    public int maxPathSumNaive(TreeNode root) {
        return maxPathNaive(root);
    }

    // ---------------- 2. DFS with Global Variable (Standard Optimal Approach) ----------------
    // Time: O(n), Space: O(h)
    private int globalMax;
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        globalMax = Math.max(globalMax, root.val + left + right);
        return root.val + Math.max(left, right); // return max downward path
    }
    public int maxPathSum(TreeNode root) {
        globalMax = Integer.MIN_VALUE;
        dfs(root);
        return globalMax;
    }

    // ---------------- 3. Pair-based DFS (Structured DP-on-tree) ----------------
    // Return: [maxDownward, maxPath]
    private int[] dfsPair(TreeNode root) {
        if (root == null) return new int[]{0, Integer.MIN_VALUE};
        int[] left = dfsPair(root.left);
        int[] right = dfsPair(root.right);
        int maxDownward = Math.max(0, Math.max(left[0], right[0])) + root.val;
        int maxPath = Math.max(left[1], right[1]);
        maxPath = Math.max(maxPath, left[0] + right[0] + root.val);
        return new int[]{maxDownward, Math.max(maxPath, maxDownward)};
    }
    public int maxPathSumPair(TreeNode root) {
        return dfsPair(root)[1];
    }
}