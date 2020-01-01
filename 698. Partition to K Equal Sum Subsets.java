https://leetcode.com/problems/partition-to-k-equal-sum-subsets/

思路：Recursion with Memoization + Bit Compression

时间复杂度：O(n * 2^n)
空间复杂度：O(2^n)

class Solution {
    private int dfs(int[] nums, int sum, int remain, int[] dp, int used) {
        /* base case */
        if (used == (1 << nums.length) - 1) {
            return remain == 0 ? 1 : -1;
        }
        if (remain < 0) {
            return -1;
        }
        /* use memoization */
        if (dp[used] != 0) {
            return dp[used];
        }
        
        /* update remain */
        if (remain == 0) {
            remain = sum;
        }
        
        boolean canSum = false;
        for (int i = 0; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) continue; // skip if this number is used
            
            canSum |= (dfs(nums, sum, remain - nums[i], dp, used | (1 << i)) == 1);
            
            if (canSum) {
                // optimization
                break;
            }
        }
        
        dp[used] = canSum ? 1 : -1; // set memoization
        return dp[used];
    }
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num); // optimization
        }
        
        if (sum % k != 0 || max > sum / k) {
            return false;
        }
        
        sum /= k;
        int[] dp = new int[1 << nums.length];
        return dfs(nums, sum, 0, dp, 0) == 1;
    }
}