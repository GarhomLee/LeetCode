https://leetcode.com/problems/remove-nth-node-from-end-of-list/

// 解法一：Two-pass，先遍历一遍确定list size

// 解法二：One-pass，two pointers
// 1）维护left和right指针。
// 2）right先向前移动n位，然后left和right同时移动，直到right到达末尾为止，这样left和right之间的node就是n个。更新left.next即可。
//     注意：n == 0需要作为corner cases单独讨论。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) return head;
        ListNode dummy = new ListNode(-1), left = dummy, right = dummy;
        dummy.next = head;
        for (int i = 0; i < n; i++) right = right.next;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }
}