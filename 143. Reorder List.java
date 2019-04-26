https://leetcode.com/problems/reorder-list/

// 分为三步。
// 1）利用快慢指针找到linked list的“中间位置”值，可以作为模板使用，元素奇数个和偶数个找到的“中间位置”不完全相同。
// 2）记录下找到的中间位置的前后信息，然后【断开linked list】，同时【翻转后半段链表】
// 3）将前半段链表和翻转后的后半段链表依次插入

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        mid = reverse(mid);
        ListNode curr = head;
        while (mid != null) {
            ListNode midNext = mid.next;
            mid.next = curr.next;
            curr.next = mid;
            curr = mid.next;
            mid = midNext;
        }
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