https://leetcode.com/problems/maximum-number-of-points-with-cost/

idea: Dynamic Programming. See: https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344908/C%2B%2BJavaPython-3-DP-Explanation-with-pictures-O(MN)-Time-O(N)-Space
    状态函数：dp[row][col]表示选择points[row][col]时的最大总和
    初始条件：dp[0] = points[0]
    状态转移方程：如果在第row行时，遍历每个第row-1行的dp[row-1][col]得到dp[row][col]，则每行的时间复杂度为O(colLen^2)，
        总时间复杂度为O(rowLen*colLen^2)；
        实际上，可以借助两个辅助数组leftMax和rightMax。leftMax[col]表示在当前[row][col]选取所有dp[row-1][0:col]中的最大值，
        其更新方式为：leftMax[col] = Math.max(leftMax[col - 1] - 1, dp[row - 1][col])，这里leftMax[col - 1] - 1是把位置
        从col-1到col的移动的损失包含进去。同理，rightMax[col]表示在当前[row][col]选取所有dp[row-1][col:colLen-1]中的最大值。
        因此，dp更新方式为dp[row][col] = points[row][col] + Math.max(leftMax[col], rightMax[col])
time comp: O(rowLen*colLen)
space comp: O(rowLen*colLen) -> can optimize to O(colLen)

class Solution {
    public long maxPoints(int[][] points) {
        int rowLen = points.length, colLen = rowLen == 0 ? 0 : points[0].length;
        long[][] dp = new long[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            dp[0][col] = points[0][col];
        }
        
        for (int row = 1; row < rowLen; row++) {
            long[] leftMax = new long[colLen], rightMax = new long[colLen];
            
            leftMax[0] = dp[row - 1][0];
            for (int col = 1; col < colLen; col++) {
                leftMax[col] = Math.max(leftMax[col - 1] - 1, dp[row - 1][col]);
            }
            
            rightMax[colLen - 1] = dp[row - 1][colLen - 1];
            for (int col = colLen - 2; col >= 0; col--) {
                rightMax[col] = Math.max(rightMax[col + 1] - 1, dp[row - 1][col]);
            }
            
            for (int col = 0; col < colLen; col++) {
                dp[row][col] = points[row][col] + Math.max(leftMax[col], rightMax[col]);
            }
        }
        
        long max = 0;
        for (int col = 0; col < colLen; col++) {
            max = Math.max(max, dp[rowLen - 1][col]);
        }
        
        return max;
    }
}