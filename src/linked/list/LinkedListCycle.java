package linked.list;

import java.util.HashSet;

public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode end = new ListNode(-4, null);
        ListNode head = new ListNode(0, end);
        head = new ListNode(2, head);
        end.next = head;
        head = new ListNode(3, head);
        assert hasCycle(head);

        end = new ListNode(2, null);
        head = new ListNode(1, end);
        end.next = head;
        assert hasCycle(head);

        assert !hasCycle(new ListNode(2, null));
    }

    /**
     * Runtime: 4 ms, beats 14.50% of users with Java
     * Memory: Beats 15.49% of users with Java
     */
    static boolean hasCycle2(ListNode head) {
        HashSet<ListNode> nodes = new HashSet<>();

        while (head != null) {
            if (nodes.contains(head)) {
                return true;
            }
            nodes.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * Runtime: 0 ms, beats 100.00% of users with Java
     * Memory: Beats 69.19% of users with Java
     */
    static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}