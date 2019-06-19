https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

// 很明显，可以利用recursion的性质解决。
// 1）利用快慢指针模板找到中间位置，同时维护pre指针指示前一个node。找到中间位置后，将pre和slow之间断开，即pre.next = null
// 2）将链表分为3部分：第一部分为中间位置slow，作为root；第二部分为从head到pre，利用recursion得到左子树；第三部分为从slow.next到末尾，利用recursion得到右子树
// 3）因为左子树可能为null，所以需要用dummy来处理这种情况

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return convert(head);
    }
    
    private TreeNode convert(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);  // this is unnecessary
        
        ListNode dummy = new ListNode(-1), pre = dummy;  // pre indicates the end of the left part of linked list
        dummy.next = head;
        
        ListNode slow = head, fast = head;  // slow indicates the start of the right part of linked list, which means pre.next = slow
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            pre = pre.next;
        }
        
        pre.next = null;  // break linked list into left and right part;
        
        TreeNode root = new TreeNode(slow.val);
        root.left = convert(dummy.next);  // turn left part into tree
        root.right = convert(slow.next);  // turn right part into tree
        return root;
    }
}