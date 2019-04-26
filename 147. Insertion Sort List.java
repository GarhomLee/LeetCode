https://leetcode.com/problems/insertion-sort-list/

// 利用链表实现insertion sort。
// 1）可能会改变head，所以需要维护一个dummy。
// 2）遍历找到curr.val > curr.next.val的位置，把curr.next记录为next同时更新curr.next为下一个node，然后用pre从前往后找到next应该插入的位置

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-100), curr = head;
        dummy.next = head;
        while (curr.next != null) {
            if (curr.val <= curr.next.val) curr = curr.next;
            else {
                ListNode next = curr.next, pre = dummy;
                curr.next = next.next;
                while (pre.next.val < next.val) pre = pre.next;
                next.next = pre.next;
                pre.next = next;
            }
        }
        return dummy.next;
    }
}
