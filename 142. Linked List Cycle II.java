https://leetcode.com/problems/linked-list-cycle-ii/

// 141. Linked List Cycle的follow-up，要求如果有cycle，找到cycle的起始node。
// 前半段代码和141题一样，不同之处在于，当slow == fast时，利用cycle的数学性质可知，用第三个指针search从head开始每次走一步，当它和slow相遇的node就是cycle的起始node。

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode search = head;
                while (search != slow) {
                    search = search.next;
                    slow = slow.next;
                }
                return search;
            }
        }
        return null;
    }
}