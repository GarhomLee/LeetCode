https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

idea: DP
time comp: O(n)
space comp: O(1)

class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int buy = Integer.MAX_VALUE, sell = Integer.MIN_VALUE;
        for (int curr: prices) {
            if (curr < buy) {
                buy = curr;
                sell = curr;
            } else {
                sell = Math.max(sell, curr);
                max = Math.max(max, sell - buy);
            }
        }
        
        return max;
    }
}