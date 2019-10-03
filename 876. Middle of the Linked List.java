https://leetcode.com/problems/middle-of-the-linked-list/

// 思路：Two Pointers (fast & slow)
//         注意，题目要求如果LinkedList有偶数个ListNode，那么要返回中间靠右的一个。
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.题意理解错误：input的head指向的就是第一个ListNode，而不是第一个前的dummy node。

class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        //ListNode slow = head.next, fast = head.next;  // {Mistake 1}
        ListNode slow = head, fast = head;  // {Correction 1}
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return fast.next == null ? slow : slow.next;
    }
}