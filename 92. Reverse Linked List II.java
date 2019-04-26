https://leetcode.com/problems/reverse-linked-list-ii/

// 206. Reverse Linked List的follow-up。
// 1）由于可能会改变head的值，所以需要维护一个dummy node作为sentinel
// 2）找到需要翻转的左边界，利用joint记录翻转前断层左边的连接处，tail记录翻转后的断层尾部
// 3）从位置m到n（包含n），利用模板翻转链表
// 4）翻转后，pre指向翻转后的断层头部，curr指向右边的未被翻转的第一个位置。利用joint和pre连接，tail和curr连接。
// 5）返回dummy.next

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1), curr = head, pre = dummy;
        dummy.next = head;
        int pos = 1;
        while (pos < m) {
            pre = curr;
            curr = curr.next;
            pos++;
        }
        ListNode joint = pre, tail = curr;
        pre = null;
        while (pos <= n) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
            pos++;
        }
        joint.next = pre;
        tail.next = curr;
        return dummy.next;
    }
}