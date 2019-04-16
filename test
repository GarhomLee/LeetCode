https://leetcode.com/problems/maximum-subarray/

// test 1, 2, 3 



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
