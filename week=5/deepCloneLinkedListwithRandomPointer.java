import java.util.HashMap;
public class deepCloneLinkedListwithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) { this.val = val; }
        public Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    // Approach 1: Using HashMap
    // Basic Idea:
    // 1. Create a clone of each node and store mapping oldNode -> newNode in a HashMap.
    // 2. Use the map to set 'next' and 'random' pointers for all cloned nodes.
    // Time: O(n), Space: O(n)
    public Node copyRandomListHashMap(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }

    // Approach 2: Without extra space
    // Basic Idea:
    // 1. Insert cloned nodes right after each original node in the list.
    // 2. Set cloned nodes' random pointers using original nodes' random pointers.
    // 3. Separate the original list from the cloned list.
    // Time: O(n), Space: O(1)
    public Node copyRandomListNoExtraSpace(Node head) {
        if (head == null) return null;
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        curr = head;
        while (curr != null) {
            if (curr.random != null) curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
        Node dummy = new Node(0), copyCurr = dummy;
        curr = head;
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        return dummy.next;
    }
}