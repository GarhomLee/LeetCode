https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/

idea: Prefix Sum
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minStartValue(int[] nums) {
        int sum = 0, minSum = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            minSum = Math.min(minSum, sum);
        }
        
        return Math.max(1 - minSum, 1);
    }
}