https://leetcode.com/problems/palindrome-linked-list/

// 分三步：
// 1）用快慢指针模板找到中间位置
// 2）将链表从中间位置断开，将后半段链表翻转
// 3）将前半段链表的node和翻转后的后半段链表元素逐一比较

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next, curr = head;
        slow.next = null;
        mid = reverse(mid);
        while (mid != null) {
            if (mid.val != curr.val) return false;
            mid = mid.next;
            curr = curr.next;
        }
        return true;
    }
    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode curr = node, pre = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}