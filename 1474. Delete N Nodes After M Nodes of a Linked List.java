https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/

idea: Two pointers
time complexity: in-place O(n)
space complexity: O(1)

class Solution {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0), pre = dummy, curr = head;
        dummy.next = head;
        while (curr != null) {
            for (int i = 0; i < m && curr != null; i++) {
                pre = curr;
                curr = curr.next;
            }
            
            for (int j = 0; j < n && curr != null; j++) {
                curr = curr.next;
            }
            
            pre.next = curr;
        }
        return dummy.next;
    }
}