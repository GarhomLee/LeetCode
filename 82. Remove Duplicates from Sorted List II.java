https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

// 83. Remove Duplicates from Sorted List的follow-up，要求只要出现了重复，所有重复的node都要删除，难度增加。由于缺少确定的可用来比较的值，所以【不能用linked list拆分模板】。

// 解法一：iteration
// 1）可能会改变head的值，所以需要维护一个dummy node作为sentinel
// 2）维护pre指针，指向去重后的linked list的最后一个node。维护curr指针，指向当前扫描到的node
// 3）在每次循环中，先进行curr一直扫描到和下一个node的值不同或下一个为null
// 4）扫描开始前，根据定义，pre.next即为curr。curr扫描停止后，【比较pre.next指向的node和curr指向的node】（注意：不是pre和curr的值）。
//     如果pre.next == curr，说明curr没有移动，且curr.next和curr值不同（或为null），因此更新pre。否则pre.next != curr，说明curr移动了，
//     那么从pre.next到curr的所有node都要删除，所以更新pre.next为curr.next。
// 5）记得要更新curr = curr.next。

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
        ListNode dummy=new ListNode(-1), pre = dummy, curr = head;
        dummy.next = head;
        while (curr != null) {
            while (curr.next != null && curr.val == curr.next.val) curr = curr.next;
            
            if (pre.next == curr) pre = pre.next;
            else pre.next = curr.next;
            
            curr = curr.next;
        }
        return dummy.next;
    }
}

// 解法二：recursion

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-100);
        dummy.next = head;
        if (head.val != head.next.val) {
            dummy.next.next = deleteDuplicates(dummy.next.next);
        } else {
            while (head.next != null && head.val == head.next.val) head = head.next;
            dummy.next = deleteDuplicates(head.next);
        }
        return dummy.next;
    }
}