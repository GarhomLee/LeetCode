https://leetcode.com/problems/remove-linked-list-elements/

// 可能会改变head，所以需要维护一个dummy。【只有当curr.next.val != val时curr才能更新】

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-100), curr = dummy;
        dummy.next = head;
        while (curr.next != null) {
            if (curr.next.val == val) curr.next = curr.next.next;
            else curr = curr.next;
        }
        return dummy.next;
    }
}