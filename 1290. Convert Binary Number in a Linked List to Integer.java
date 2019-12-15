https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/

// 思路：LinkedList + Math
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int getDecimalValue(ListNode head) {
        int res = 0;
        ListNode curr = head;
        while (curr != null) {
            res = res * 2 + curr.val;
            curr = curr.next;
        }
        return res;
    }
}