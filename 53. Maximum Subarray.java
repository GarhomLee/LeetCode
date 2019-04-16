https://leetcode.com/problems/maximum-subarray/

// 入门级DP题。
// 1）维护两个变量：currSum表示当前nums[i]存在时的局部最大值；maxSum表示全局最大值。
// 2）当currSum < 0时，不管nums[i]是正是负，currSum + nums[i]一定比nums[i]小，所以重置currSum为nums[i]
// 3）其他情况，自加nums[i]。
// 4）在遍历过程中更新maxSum。

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