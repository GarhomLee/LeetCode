https://leetcode.com/problems/partition-list/

// Two pointers问题，有两种解法。

// 解法一：
// 1）可能会改变head的值，所以需要维护一个dummy node作为sentinel
// 2）维护left指针，指向小于x的最后一个node。先确定left指针的位置。
// 3）维护right指针，初始化为left的位置。如果right.next.val < x，将right.next插入left的下一位。更新left.next和right.next。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1), left = dummy;
        dummy.next = head;
        while (left.next != null && left.next.val < x) left = left.next;
        
        ListNode right = left;
        while (right.next != null) {
            if (right.next.val >= x) right = right.next;
            else {
                ListNode next = right.next;
                right.next = next.next;
                next.next = left.next;
                left.next = next;
                left = left.next;
            }
        }
        return dummy.next;
    }
}

// 解法二：LeetCode给出的参考solution，可以作为拆分linked list的模板。注意使用条件：需要知道用来比较的【标准值】
// 1）维护两个sentinel node，分别指向拆分后小于x的半边linked list和大于等于x的半边linked list的头部。再维护两个变量，指向这两个linked list的尾部。
// 2）扫描linked list，跟【标准值x比较】。如果小于x，接到before后，然后更新before；否则，接到after后，然后更新after

class Solution {
    public ListNode partition(ListNode head, int x) {

        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }
}