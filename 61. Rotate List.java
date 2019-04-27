https://leetcode.com/problems/rotate-list/

// 1）corner cases: k <= 0，直接返回head
// 2）可能会改变head，所以需要维护一个dummy node作为sentinel
// 3）这题没有办法做到one-pass，需要先遍历一遍整个linked list确定size，将首尾相连，然后再在size - k处断开

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) return head;
        int size = 0;
        ListNode dummy = new ListNode(-1), curr = dummy;
        dummy.next = head;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }
        k = k % size;
        if (k == 0) return dummy.next;
        curr.next = dummy.next;
        while (size - k > 0) {
            curr = curr.next;
            k++;
        }
        dummy.next = curr.next;
        curr.next = null;
        return dummy.next;
    }
}