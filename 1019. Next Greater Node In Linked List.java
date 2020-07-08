https://leetcode.com/problems/next-greater-node-in-linked-list/

idea: Stack (monotonic queue)
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        
        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        
        int n = list.size();
        int[] ret = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }
            
            ret[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        
        return ret;
    }
}