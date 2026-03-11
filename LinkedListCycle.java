public class LinkedListCycle {

    /**
     * Inner static node class representing a singly linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Detects whether the linked list contains a cycle using Floyd's
     * slow/fast pointer (tortoise and hare) algorithm.
     * Time complexity: O(n), Space complexity: O(1).
     *
     * @param head the head node of the linked list
     * @return true if a cycle exists, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Build list: 3 -> 2 -> 0 -> -4, with -4 pointing back to 2 (cycle)
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // cycle
        System.out.println("hasCycle([3->2->0->-4->2...]) -> " + solution.hasCycle(head1)); // true

        // Build list: 1 -> 2, no cycle
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        System.out.println("hasCycle([1->2])              -> " + solution.hasCycle(head2)); // false

        // Single node, no cycle
        ListNode head3 = new ListNode(1);
        System.out.println("hasCycle([1])                 -> " + solution.hasCycle(head3)); // false
    }
}
