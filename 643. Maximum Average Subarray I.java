https://leetcode.com/problems/maximum-average-subarray-i/

idea: Sliding Window
time complexity: O(n)
space complexity: O(1)

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i + 1 - k >= 0) {
                maxSum = Math.max(maxSum, sum);
                sum -= nums[i + 1 - k];
            }
        }
        
        return ((double) maxSum) / k;
    }
}