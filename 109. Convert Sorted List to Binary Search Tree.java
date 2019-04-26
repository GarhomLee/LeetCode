https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

// 很明显，可以利用recursion的性质解决。
// 1）利用快慢指针模板找到中间位置，同时维护pre指针指示前一个node。找到中间位置后，将pre和slow之间断开，即pre.next = null
// 2）将链表分为3部分：第一部分为中间位置slow，作为root；第二部分为从head到pre，利用recursion得到左子树；第三部分为从slow.next到末尾，利用recursion得到右子树
// 3）因为左子树可能为null，所以需要用dummy来处理这种情况

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head, fast = head, pre = dummy;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(dummy.next);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}