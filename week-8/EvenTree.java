import java.util.*;
public class EvenTree {
    static List<List<Integer>> adj;   // adjacency list
    static int removableEdges = 0;    // result counter
    /**
     * DFS to compute subtree sizes
     * TC: O(N) -> Each node and edge is visited once
     * SC: O(N) -> Recursion stack + adjacency list
     */
    public static int dfs(int node, int parent) {
        int subtreeSize = 1;  // count current node
        for (int child : adj.get(node)) {
            if (child != parent) {
                int childSize = dfs(child, node);
                // If child's subtree is even, cut edge (node-child)
                if (childSize % 2 == 0) {
                    removableEdges++;
                } else {
                    subtreeSize += childSize; // otherwise add to parent’s size
                }
            }
        }
        return subtreeSize;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        // build adjacency list
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(1, -1);  // start DFS from root (node 1)
        System.out.println(removableEdges);
    }
}