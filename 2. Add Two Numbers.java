https://leetcode.com/problems/add-two-numbers/

// 利用数学性质解决简单加法的linked list实现。
// 1）维护carry表示进位后要加的数
// 2）将carry和l1.val及l2.val相加（需要先判断是否为空）。更新l1和l2（不为空时，已经被加）
// 3）跳出循环后，还需判断carry是否为0

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            ListNode curr = new ListNode(carry);
            if (l1 != null) {
                curr.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                curr.val += l2.val;
                l2 = l2.next;
            }
            carry = curr.val / 10;
            curr.val = curr.val % 10;
            pre.next = curr;
            pre = pre.next;
        }
        if (carry != 0) pre.next = new ListNode(carry);
        return dummy.next;
    }
}