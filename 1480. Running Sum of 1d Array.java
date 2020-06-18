https://leetcode.com/problems/running-sum-of-1d-array/

idea: Just prefix sum
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = nums[i] + res[i - 1];
        }
        
        return res;
    }
}