https://leetcode.com/problems/find-the-most-competitive-subsequence/

idea: Constrained Stack/Monotonoic Queue
time complexity: O(n)
space complexity: O(k)

class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i] && stack.size() + n - i > k) {
                stack.pop();
            }
            if (stack.size() < k) {
                stack.push(i);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            res[i] = nums[stack.pop()];
        }
        return res;
    }
}