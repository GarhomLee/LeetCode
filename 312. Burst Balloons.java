https://leetcode.com/problems/burst-balloons/

// 解法一：Top-down Memoization
//         如果打破位置为k的气球nums[k]，同时已知打破nums[1:k-1]和nums[k+1:n]的收益，那么就可以求出总收益。要注意的是，这里的nums[k]不是最先打破（即求nums[k-1]*nums[k]*nums[k+1]），
//         而是在打破了左右两边所有的气球后再最后打破（即nums[start-1]*nums[k]*nums[end+1]）。因为如果按前者的求法，打破nums[k]后，nums[k-1]就和nums[k+1]相邻，而求取dp[start][k-1]
//         时需要用到nums[k-1]和nums[k]相邻，所以把nums[k]放到最后打破，才符合dp数组的定义。
//         在用Recursion时，已经求取过的dp值将大于0。
// 时间复杂度：O(n^3) ?
// 空间复杂度：O(n^2)
// 小技巧：在nums数组前后加value为1的padding，方便计算。

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n + 2];  // insert paddings
        System.arraycopy(nums, 0, copy, 1, n);
        copy[0] = copy[n + 1] = 1;
        nums = copy;
        int[][] dp = new int[n + 2][n + 2];  // dp[i][j] indicates maximum coins when nums[i:j] are bursted

        return burst(nums, dp, 1, n);
    }
    /** helper method to find maximum coins when nums[start:end] are bursted */
    private int burst(int[] nums, int[][] dp, int start, int end) {
        if (dp[start][end] > 0) return dp[start][end];  // use memoization result
        if (start > end) return 0;  // termination condition
        
        for (int k = start; k <= end; k++) {
            dp[start][end] = Math.max(dp[start][end], burst(nums, dp, start, k - 1) + nums[start - 1] * nums[k] * nums[end + 1] + burst(nums, dp, k + 1, end));
        }
        return dp[start][end];
    }
}

// 解法二：Bottom-up DP
//         状态函数：dp[i][j]表示气球nums[i:j]破掉能够获得的最大收益
//         状态转移方程：对于不同的k的选取，dp[start][end]取所有可能的dp[start][k - 1] + nums[start - 1] * nums[k] * nums[end + 1] + dp[k + 1][end]
//             的最大值。由于要先知道dp[start][k-1]和dp[k+1][end]，所以要从比较小的rangeLength开始求。确定start和rangeLength后，end随之确定。
//         初始值：dp[i][j] = 0
// 时间复杂度：O(n^3)
// 空间复杂度：O(n^2)
// 犯错点：1.注意start的终止值，因为需要保证end <= n，所以对于某一确定的rangeLength，需要保证start <= n - rangeLength + 1
// 小技巧：在nums数组前后加value为1的padding，方便计算。


class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n + 2];
        System.arraycopy(nums, 0, copy, 1, n);
        copy[0] = copy[n + 1] = 1;
        nums = copy;
        int[][] dp = new int[n + 2][n + 2];
        
        for (int rangeLength = 1; rangeLength <= n; rangeLength++) {
            for (int start = 1; start <= n - rangeLength + 1; start++) {  // {Mistake 1} {Correction 1}
                int end = start + rangeLength - 1;
                for (int k = start; k <= end; k++) {
                    dp[start][end] = Math.max(dp[start][end], dp[start][k - 1] + nums[start - 1] * nums[k] * nums[end + 1] + dp[k + 1][end]);
                }
            }
        }
        return dp[1][n];
    }
}