https://leetcode.com/problems/maximum-subarray/

// 入门级DP题。

// 解法一：Bottom-up DP
//         状态函数：1-based的一维dp数组，dp[i]表示包含nums[i-1]在内的nums数组连续元素的局部最大和
//         状态转移方程：如果dp[i-1]>=0，直接与当前nums[i-1]相加；如果dp[i-1]<0，那么一定有dp[i-1]+nums[i-1]<nums[i-1]。
//             所以dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1])。
//             同时不断更新maxSum为全局最大值。
//         初始值：dp[i]=0，无需特别指定。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length + 1];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}

// 优化：由于dp[i]只和dp[i-1]有关，所以可以将空间复杂度O(n) -> O(1)
//      1）维护两个变量：currSum表示当前nums[i]存在时的局部最大值；maxSum表示全局最大值。
//      2）当currSum < 0时，不管nums[i]是正是负，currSum + nums[i]一定比nums[i]小，所以重置currSum为nums[i]
//      3）其他情况，自加nums[i]。
//      4）在遍历过程中更新maxSum。

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int currSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (currSum < 0) currSum = nums[i];
            else currSum += nums[i];
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}