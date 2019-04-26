https://leetcode.com/problems/linked-list-cycle/

// 经典的快慢指针题。注意：虽然两个指针都初始化为head，但实际上是先走了一步再进行if判断。可对比287. Find the Duplicate Number的快慢指针。

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
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }
}