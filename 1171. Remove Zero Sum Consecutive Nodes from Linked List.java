https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/

// 思路：HashMap + Stack
//         计算prefix sum，用HashMap记录。key为出现过的sum，value为对应的ListNode。
//         为了应对[1,2,3,-2,-3,10,5,-5,1]这样的情况，可以维护一个Stack，记录从左往右的当前存在的sum。
//         由于可能要删除第一个节点，所以维护dummy，使得dummy.next = head。
//         初始化，将0和dummy放进HashMap，将0放进Stack。
//         遍历Linked List，计算prefix sum。可能有两种情况：
//         1）这个sum是目前第一次出现，那么将sum和当前ListNode curr放进HashMap中。
//         2）找到了出现过的sum，那么需要找到之前出现的位置，同时将中间出现的其他sum清除掉。利用Stack可以实现，
//             将Stack的顶部元素都删除，直到找到sum。同时需要将HashMap中的这些sum也清除掉。
//             将sum之前出现的位置到当前位置的所有ListNode删除，即map.get(sum).next = curr.next。
//         不论哪种情况，都要更新curr=curr.next。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        map.put(sum, dummy);
        Stack<Integer> stack = new Stack();
        stack.push(sum);
        
        ListNode curr = head;
        while (curr != null) {
            sum += curr.val;
            if (!map.containsKey(sum)) {
                map.put(sum, curr);
                stack.push(sum);
            } else {
                while (!stack.isEmpty() && stack.peek() != sum) {
                    map.remove(stack.pop());
                }
                map.get(sum).next = curr.next;
            }
            curr = curr.next;
        }
        
        return dummy.next;
    }
}