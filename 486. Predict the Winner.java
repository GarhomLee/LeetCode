https://leetcode.com/problems/predict-the-winner/

// 思路：DP问题。

// 解法一：Top-down Recusion with Memoization
//         先做预处理，求出nums数组元素的和sum。调用递归函数后，比较其结果是否大于等于sum的一半（要考虑sum为奇数的情况），
//         表示玩家1得到的分数和玩家2相等或更多。
//         递归函数定义：int dfs(int[] nums, int left, int right, int sum)表示当前玩家在给定nums[left:right]
//                 范围内能取得的score的最大值。sum为当前范围nums[left:right]的和。
//                 同时，维护二维Integer数组dp，记录给定nums[left:right]的结果。由题意可以，如果nums[left:right]给定，
//                 那么结果也一定确定，因此可以用memoization来减小计算量。用Integer数组而不用int数组是因为不需要将数组特别
//                 初始化，用null就可以表示没有遍历过。
//         终止条件：1）left == right，只剩一个元素，返回nums[left]
//                 2）dp[left][right] != null，给定nums[left:right]的结果已经求解过了，直接返回dp[left][right]
//         递归过程：记leftMax为当前玩家拿了左边缘元素后，下一轮玩家在剩下的范围内总共能得到的最大分数，调用递归函数来得到；
//                 同理rightMax为当前玩家拿了右边缘元素后，下一轮玩家在剩下的范围内总共能得到的最大分数，也是调用递归函数来得到。
//                 因此，当前玩家在当前nums[left:right]范围内得到的最大分数，等于于sum减去leftMax和rightMax的最小值。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n^2)

class Solution {
    Integer[][] dp;
    
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        dp = new Integer[nums.length][nums.length];
        return dfs(nums, 0, nums.length - 1, sum) >= (sum + 1) / 2;
    }
    
    private int dfs(int[] nums, int left, int right, int sum) {
        /* base case */
        if (left == right) {
            return nums[left];
        }
        if (dp[left][right] != null) return dp[left][right];  // use memoization
        
        int leftMax = dfs(nums, left + 1, right, sum - nums[left]);
        int rightMax = dfs(nums, left, right - 1, sum - nums[right]);
        int currMax = sum - Math.min(leftMax, rightMax);

        dp[left][right] = currMax;  // set memoization
        return currMax;
    }
}


解法二：Bottom-up DP

优化：空间降维，滚动数组