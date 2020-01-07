https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

系列总结：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems

思路：DP。参考：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/
        维护变量：sell，表示按当天价格prices[i]卖出后能得到的最大总利润；buy，表示按当天价格
        prices[i]买入后能得到的最大总利润。
时间复杂度：O(n)
空间复杂度：O(1)
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // sell before buy
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        
        return sell;
    }
}