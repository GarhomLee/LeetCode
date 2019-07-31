https://leetcode.com/problems/target-sum/

// 思路：DP。视频讲解：https://www.youtube.com/watch?v=r6Wz4W1TbuI
//         可以认为是0/1背包问题的变种，这里的取值范围可以为[-sum:sum]，即可以取负数，为了使它可以利用array的index
//         来表示，设置一个offset，将负数平移offset位，那么最小的负数就会平移到0。nums数组所有元素为非负数，
//         offset可以定为nums数组所有元素之和sum。
//         先做一个预判断，如果sum比target绝对值要小，那么不可能找到任何方法得到target，所以直接返回0。
//         状态函数：dp[i][j]，表示利用nums数组前i个元素（1-based），能得到的和为j的方法数，注意j中包含了offset。
//         状态转移方程：对于当前元素nums[i - 1]，一定会加入，有两种加入方式：
//                 如果加的是正数，那么dp[i][j]=dp[i - 1][j - nums[i - 1]]；
//                 如果加的是负数，那么dp[i][j]=dp[i - 1][j - (-nums[i - 1])]；
//                 综合两种情况，dp[i][j]可以由这两种状态之一转移而来，所以dp[i][j]是它们的加和。
//                 特殊情况下，如果j - nums[i - 1]<0，超出范围，那么这一项的值为0；同理，j - (-nums[i - 1]) > 2 * sum
//                 对应项的值为0.
//         初始值：dp[0][offset] = 1
//         最后的结果要【返回dp[nums.length][target + offset]】。
// 时间复杂度：O(n*sum)
// 空间复杂度：O(n*sum)

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        /* corner case */
        if (sum < Math.abs(target)) return 0;
        
        int offset = sum;
        int[][] dp = new int[nums.length + 1][2 * sum + 1];
        dp[0][offset] = 1;  // initial value: there is 1 way to use no element in nums array to make up sum = offset - offset = 0
        
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= 2 * sum; j++) {
                int waysBeforeAddingPositive = j - nums[i - 1] >= 0 ? dp[i - 1][j - nums[i - 1]] : 0;
                int waysBeforeAddingNegative = j - (-nums[i - 1]) <= 2 * sum ? dp[i - 1][j - (-nums[i - 1])] : 0;
                dp[i][j] = waysBeforeAddingPositive + waysBeforeAddingNegative;
            }
        }
        
        return dp[nums.length][target + offset];
    }
}