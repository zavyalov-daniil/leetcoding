package linked.list;

public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode head = new ListNode(4, null);
        head = new ListNode(2, head);
        head = new ListNode(1, head);
        ListNode head2 = new ListNode(4, null);
        head2 = new ListNode(3, head2);
        head2 = new ListNode(1, head2);
        ListNode newHead = mergeTwoListsRecursion(head, head2);
        assert newHead.val == 1;
        newHead = newHead.next;
        assert newHead.val == 1;
        newHead = newHead.next;
        assert newHead.val == 2;
        newHead = newHead.next;
        assert newHead.val == 3;
        newHead = newHead.next;
        assert newHead.val == 4;
        newHead = newHead.next;
        assert newHead.val == 4;

        head = new ListNode(5);
        head2 = new ListNode(4);
        newHead = mergeTwoLists(head, head2);
        assert newHead.val == 4;
        newHead = newHead.next;
        assert newHead.val == 5;
    }

    /**
     * Runtime: 0 ms
     * Memory: Beats 11.71-65.65% of users with Java
     */
    static ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val > list2.val) {
            list2.next = mergeTwoListsRecursion(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoListsRecursion(list2, list1.next);
            return list1;
        }
    }

    /**
     * Runtime: 0 ms
     * Memory: Beats 65.65% of users with Java
     */
    static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            return merge(list1, list2);
        } else {
            return merge(list2, list1);
        }
    }

    private static ListNode merge(ListNode current1, ListNode current2) {
        ListNode head = current1;
        while (current1.next != null && current2 != null) {
            if (current1.val != current2.val && current1.next.val < current2.val) {
                current1 = current1.next;
            } else {
                ListNode buffer1 = current1.next;
                ListNode buffer2 = current2.next;
                current1.next = current2;
                current1 = current1.next;
                current1.next = buffer1;
                current2 = buffer2;
            }
        }
        if (current1.next == null) {
            current1.next = current2;
        }
        return head;
    }

}