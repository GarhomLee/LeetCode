https://leetcode.com/problems/combination-sum-iv/

// 总体思路：严格来说，应该是要求所有可能的Permutation数，而不是Combination数。
//         可以用DP来做。因为对于某个小于target的数i，如果target - i在nums数组里作为candidate，那么能组成和为i的Permutation数
//         对于组成和为target的Permutation数会有贡献，所以需要找到所有的i，它们的Permutation数的和就是target的Permutation数，符合
//         重复子问题的定义。同时，对于确定的i和给定的nums数组，能组成的Permutation数是确定的，符合无后效性。
//         状态函数：维护一维dp数组，dp[i]表示能组成和为i(i > 0)的Permutation数。
//         状态转移方程：dp[i] = dp[i - nums[0]] + ... + dp[i - nums[j]]，当nums[j] <= i
//         初始值：dp[0] = 1。注意dp[0]的含义，和i > 0不同，当dp[i]的i在nums数组里，那么会有一个Permutation只有i这一个数，所以dp[i - i] = dp[0] = 1。
// 时间复杂度：O(n^2)，n = target
// 空间复杂度：O(n)
// 犯错点：1.初始化条件dp[0] = 1，如果不赋值dp[0]为1，那么即使对于nums = [1], target = 1，得到的dp[1]是错的，后续都会是错的。

class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        Arrays.sort(nums);  // sort nums array first for optimization in the nested for-loop
        int[] dp = new int [target + 1];  // dp[i] indicates the number of possible Permutations adding up to i
        dp[0] = 1;  // {Mistake 1: initialzation, dp[0] is for putting a num in nums array.} {Correction 1}
        
        for (int i = 1; i <= target; i++) {  // iterate all numbers before target
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {  // iterate all candidates in nums array less than i
                dp[i] += dp[i - nums[j]];  // the number of possible Permutations adding up to i comes from all dp values associated with i - nums[j], where nums[j] <= i
            }
        }
        
        return dp[target];
    }
}