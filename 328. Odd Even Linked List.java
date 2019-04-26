https://leetcode.com/problems/odd-even-linked-list/

// 可以看作143. Reorder List的follow-up，或者是143. Reorder List的逆过程。
// 1）维护evenHead作为even链表的起始；同时维护odd和even作为扫描的pointer，使得【odd永远在even的前一位】
// 2）以even作为循环判断的条件，当even为空或even.next为空，那么odd可以直接连接到evenHead
// 3）在循环中，依次拆开odd node和even node，并更新odd和even

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode evenHead = head.next, odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}