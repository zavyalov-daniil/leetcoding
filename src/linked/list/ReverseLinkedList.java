package linked.list;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head = new ListNode(3, head);
        head = new ListNode(2, head);
        head = new ListNode(1, head);
        ListNode newHead = reverseList(head);
        assert newHead.val == 4;
        newHead = newHead.next;
        assert newHead.val == 3;
        newHead = newHead.next;
        assert newHead.val == 2;
        newHead = newHead.next;
        assert newHead.val == 1;

    }

    static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

}