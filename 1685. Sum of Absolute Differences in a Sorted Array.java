https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/

idea: Math + Prefix Sum
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i < n; i++) {
            // (nums[i] - nums[0]) + ... + (nums[i] - nums[i]) 
            // + (nums[i + 1] - nums[i]) + ... + (nums[n - 1] - nums[i])
            res[i] = nums[i] * (i + 1) - sum[i + 1] + (sum[n] - sum[i + 1]) - nums[i] * (n - (i + 1));
        }
        
        
        return res;
    }
}