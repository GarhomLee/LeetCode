https://leetcode.com/problems/intersection-of-two-linked-lists/

// 可以归属于Two pointers问题。
// 1）维护两个pointer：nodeA和nodeB，分别初始化为headA和headB
// 2）当nodeA != nodeB时，进入循环。【如果nodeA或nodeB到达了null，那么更新为另一个链表的头】，否则更新为当前的next
// 3）循环结束，nodeA == nodeB有两种情况：
//     （a）都为null
//     （b）都为非null的intersection node
//     无论是哪种情况，都可以作为返回结果。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = (nodeA == null) ? headB : nodeA.next;
            nodeB = (nodeB == null) ? headA : nodeB.next;
        }
        return nodeA;
    }
}