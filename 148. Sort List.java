https://leetcode.com/problems/sort-list/

// 要求时间复杂度为O(n log n)，所以需要实现merge sort。
// 1）利用快慢指针模板找到中间位置，将链表断开，将前半段的结尾设为null
// 2）用recursion将左右两半链表排好序
// 3）将两个排好序的链表merge起来（见21. Merge Two Sorted Lists），然后返回左边链表的head

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        head = sortList(head);
        mid = sortList(mid);
        head = merge(head, mid);
        return head;
    }
    private ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        if (head1.val < head2.val) {
            head1.next = merge(head1.next, head2);
            return head1;
        }
        else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }
}