https://leetcode.com/problems/strange-printer/

// 对比：546. Remove Boxes

// 思路：Recursion with Memoization。难度较高。视频详解：https://www.youtube.com/watch?v=YQQUGsb7mww
//         递归函数定义：int dfs (char[] chars, int start, int end, int[][] dp)表示利用题目给出的printer
//                     得到chars[start:end]的字符组合所需的最小步数。根据题意，最后返回的是dfs(s.toCharArray(), 0, len - 1, dp)。
//                     辅助数组dp[start][end]定义相同。
//         终止条件：1）start>end，不是合法字符组合，返回0
//                 2）dp[start][end] > 0，表示chars[start:end]的结果已经求解过，直接返回
//         递归过程：变量res初始化为特殊情况，即chars[start:end-1]没有任何字符和chars[end]相同，所以结果是
//                 求解chars[start:end-1]的结果然后+1。
//                 如果chars[start:end-1]中，存在和chars[end]相同的字母chars[k]，那么根据题意，打印出chars[k]
//                 时实际上可以连续打印出chars[k:end]，那么实际上以chars[k]将chars[start:end]分为两部分：
//                 1）chars[k+1:end-1]，即把chars[k:end]中间覆盖掉的部分，调用递归函数dfs(chars, k + 1, end - 1, dp)
//                     求出最小步数；
//                 2）chars[start:k]（chars[k:end]是连续打印的，可以不用额外考虑），即前半部分，调用递归函数
//                     dfs(chars, start, k, dp)求出最小步数
//                 综合两种情况，res为两部分步数之和。对于不同的分割点chars[k]，res取所有分割点所能得到的最小步数。
// 注意：和546. Remove Boxes不同，这题不需要做将end指针移动到第一个不同字母的优化。      
// 时间复杂度：O(n^3)
// 空间复杂度：O(n^2)

class Solution {
    public int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        return dfs(s.toCharArray(), 0, len - 1, dp);
    }
    
    private int dfs (char[] chars, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end] > 0) {
            return dp[start][end];
        }
        
        /*while (start < end && chars[end] == chars[end - 1]) {
            end--;
        }*/ // unnecessary
        int res = dfs(chars, start, end - 1, dp) + 1;  // worst case where no char is the same as chars[end]
        for (int k = start; k < end; k++) {
            if (chars[k] == chars[end]) {
                res = Math.min(res, dfs(chars, start, k, dp) + dfs(chars, k + 1, end - 1, dp));
            }
        }
        dp[start][end] = res;
        return res;
    }
}