https://leetcode.com/problems/house-robber/

// 解法一：Top-down Memoization
//         根据题意，对于当前的nums[i]，有两种选择：要么取nums[i]，那么就不能取nums[i-1]；要么不取nums[i]，那么就可以取nums[i-1]。
//         所以当前的最大值应该是这两种情况其中之一。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.dp数组需要初始化为1，因为nums数组可能值全为0
//         2.需要先判断index是否小于0，保证dp[index]不会越界
//         3.比较完后要把结果存在dp[index]中，做记忆化

class Solution {
    int[] dp;
    public int rob(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);  // {Mistake 1: there is a case where all values in nums array are 0s}
        return dp(nums, nums.length - 1);
    }
    
    /** helper method to find the maximum amount of money */
    private int dp(int[] nums, int index) {        
        if (index < 0) return 0;  // {Mistake 2: this condition evaluation should come beforehand, so that index would be valid for dp[index]}
        if (dp[index] >= 0) {  
            return dp[index];
        }
        dp[index] = Math.max(dp(nums, index - 1), dp(nums, index - 2) + nums[index]);  // {Mistake 3: the result of comparison should be stored in dp array for later use, that's why it is called memoization}
        return dp[index];
    }
}

// 解法二：Bottom-up DP
//         状态函数：dp[i]表示到达nums数组第i个数（1-based，可简化代码）时能获得的最大收益
//         状态转移方程：dp[i]根据取和不取nums[i]，跟dp[i-2]+nums[i]和dp[i-1]有关，取二者较大值
//         初始值：dp[i] = 0
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int rob(int[] nums) {
        //if (nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + (i >= 2? dp[i - 2] : 0));  // Trick: when i < 2, dp[i - 2] is not valid, so it should turn to 0
        }
        return dp[nums.length];
    }
}