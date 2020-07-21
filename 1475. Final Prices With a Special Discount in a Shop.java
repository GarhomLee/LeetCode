https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/

idea: Stack (monotonic queue)
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ret = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                stack.pop();
            }
            ret[i] = prices[i] - (stack.isEmpty() ? 0 : prices[stack.peek()]);
            stack.push(i);
        }
        
        return ret;
    }
}