https://leetcode.com/problems/minimum-falling-path-sum/

对比：1289. Minimum Falling Path Sum II

思路：DP
        状态函数：
        状态转移方程：
        初始值：
时间复杂度：O(n^2)
空间复杂度：O(n^2)

class Solution {
    public int minFallingPathSum(int[][] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        /*initialize*/
        for (int col = 0; col < len; col++) {
            dp[0][col] = A[0][col];
        }
        
        for (int row = 1; row < len; row++) {
            for (int col = 0; col < len; col++) {
                dp[row][col] = Integer.MAX_VALUE;
                for (int i = -1; i <= 1; i++) {
                    int last = col + i >= 0 && col + i < len ? dp[row - 1][col + i] : Integer.MAX_VALUE - A[row][col];
                    dp[row][col] = Math.min(dp[row][col], A[row][col] + last);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < len; col++) {
            min = Math.min(min, dp[len - 1][col]);
        }
        
        return min;
    }
}


优化：in-place，时间复杂度降至O(1

class Solution {
    public int minFallingPathSum(int[][] A) {
        int len = A.length;
        for (int row = 1; row < len; row++) {
            for (int col = 0; col < len; col++) {
                int temp = A[row - 1][col];
                if (col - 1 >= 0) {
                    temp = Math.min(temp, A[row - 1][col - 1]);
                }
                if (col + 1 < len) {
                    temp = Math.min(temp, A[row - 1][col + 1]);
                }
                
                A[row][col] += temp;
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < len; col++) {
            min = Math.min(min, A[len - 1][col]);
        }
        
        return min;
    }
})