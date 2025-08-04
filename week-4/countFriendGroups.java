import java.util.*;
public class countFriendGroups {
    //DFS APPROACH
    //Time Complexity: O(N + E)
    //- N: Number of students (nodes)
    //- E: Number of friendships (edges)
    //- Each node and edge is visited exactly once.
    // Space Complexity: O(N)
    //- visited[] array of size N
    // - Recursion stack (can go up to N in worst case)
    private static void dfs(int student, Map<Integer, List<Integer>> friendship, boolean[] visited) {
        visited[student] = true;
        for (int friend : friendship.getOrDefault(student, new ArrayList<>())) {
            if (!visited[friend]) {
                dfs(friend, friendship, visited);
            }
        }
    }
    public static int countFriendGroupsDFS(int N, Map<Integer, List<Integer>> friendship) {
        boolean[] visited = new boolean[N];
        int groups = 0;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                groups++;
                dfs(i, friendship, visited);
            }
        }
        return groups;
    }

    //BFS APPROACH
    //Time Complexity: O(N + E)
    // - Similar to DFS, we visit each student and their friend connections once.

    // Space Complexity: O(N)
    // - visited[] array of size N
    // - Queue used in BFS can hold up to N students in the worst case.
    private static void bfs(int start, Map<Integer, List<Integer>> friendship, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int student = queue.poll();
            for (int friend : friendship.getOrDefault(student, new ArrayList<>())) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    queue.add(friend);
                }
            }
        }
    }
    public static int countFriendGroupsBFS(int N, Map<Integer, List<Integer>> friendship) {
        boolean[] visited = new boolean[N];
        int groups = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                groups++;
                bfs(i, friendship, visited);
            }
        }
        return groups;
    }
    public static void main(String[] args) {
        int N = 7;
        Map<Integer, List<Integer>> friendship = new HashMap<>();
        friendship.put(0, Arrays.asList(1, 2));
        friendship.put(1, Arrays.asList(0, 5));
        friendship.put(2, Arrays.asList(0));
        friendship.put(3, Arrays.asList(6));
        friendship.put(4, new ArrayList<>());
        friendship.put(5, Arrays.asList(1));
        friendship.put(6, Arrays.asList(3));
        System.out.println("DFS Friend Groups: " + countFriendGroupsDFS(N, friendship)); // Output: 3
        System.out.println("BFS Friend Groups: " + countFriendGroupsBFS(N, friendship)); // Output: 3
    }
}