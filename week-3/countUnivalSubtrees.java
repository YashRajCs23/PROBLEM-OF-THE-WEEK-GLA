public class countUnivalSubtrees {
    class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    int ans;
    // Brute-force: Recursive with type-matching at each step
    // TC: O(n), SC: O(h)
    public int singlevalued(Node root) {
        ans = 0;
        dfs(root);
        return ans;
    }
    private Integer dfs(Node root) {
        if (root == null) return null;
        Integer left = dfs(root.left);
        Integer right = dfs(root.right);
        if (root.left == null && root.right == null) {
            ans++;
            return root.data;
        }
        if (left != null && right != null && left.equals(right) && left == root.data) {
            ans++;
            return root.data;
        }
        if (root.left == null && right != null && right == root.data) {
            ans++;
            return root.data;
        }
        if (root.right == null && left != null && left == root.data) {
            ans++;
            return root.data;
        }
        return null;
    }

    // Optimal: Bottom-up / Post-order using booleans
    // TC: O(n), SC: O(h)
    public int singlevaluedPostOrder(Node root) {
        ans = 0;
        postOrderUtil(root);
        return ans;
    }
    private boolean postOrderUtil(Node root) {
        if (root == null) return true;
        boolean left = postOrderUtil(root.left);
        boolean right = postOrderUtil(root.right);
        if (left && right) {
            if ((root.left == null || root.left.data == root.data) &&
                (root.right == null || root.right.data == root.data)) {
                ans++;
                return true;
            }
        }
        return false;
    }
}