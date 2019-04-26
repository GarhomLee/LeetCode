https://leetcode.com/problems/delete-node-in-a-linked-list/

// 脑筋急转弯。把下一个node的值覆盖到当前node，然后删掉下一个node，即可完成当前node的“删除”。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}