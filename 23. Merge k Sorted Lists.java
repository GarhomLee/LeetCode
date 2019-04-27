https://leetcode.com/problems/merge-k-sorted-lists/

// 解法一：把所有ListNode整合成一个链表，然后对这个链表sort。
//     时间复杂度：O(n log n)；空间复杂度：O(n)。n为所有nodes数量

// 解法二：每次遍历array里指针所指的node，选最小的加入新链表，更新被选的node为node.next。
//     时间复杂度：O(n * k)；空间复杂度：O(n)。n为所有nodes数量，k为merge前链表的数量。

// 解法三：利用PriorityQueue优化解法二。将所有链表的头部node加入PriorityQueue，当PriorityQueue非空时，不断poll，加入新链表，同时将它的next加入PriorityQueue（如果next非空）
// 时间复杂度：O(n log k)；空间复杂度：O(n + k)。n为所有nodes数量，k为merge前链表的数量。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        PriorityQueue<ListNode> pq = new PriorityQueue<>(1, new Comparator<ListNode>() {
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        for (ListNode node: lists) {
            if (node != null) pq.offer(node);
        }
        ListNode dummy = new ListNode(-1), curr = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }
}