https://leetcode.com/problems/remove-duplicates-from-sorted-list/

// 解法一：常规解法，维护pre和curr，用curr的值和pre做比较，决定是否需要删除。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-100), pre = head, curr = head;
        dummy.next = head;
        while (curr != null) {
            if (curr.val != pre.val) {
                pre.next = curr;
                pre = pre.next;
            };
            curr = curr.next;
        }
        pre.next = null;
        return dummy.next;
    }
}

// 解法二：用linked list的拆分模板，比较的【标准值是noDup的末尾的值】

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1), dupHead = new ListNode(-1);
        dummy.next = head;
        ListNode noDup = dummy.next, dup = dupHead, curr = head.next;
        while (curr != null) {
            if (curr.val == noDup.val) {
                dup.next = curr;
                dup = dup.next;
            } else {
                noDup.next = curr;
                noDup = noDup.next;
            }
            curr = curr.next;
        }
        noDup.next = null;
        return dummy.next;
    }
}