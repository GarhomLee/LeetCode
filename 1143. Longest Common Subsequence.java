https://leetcode.com/problems/longest-common-subsequence/

// 思路：Bottom-up DP
//         状态函数：dp[i][j]表示字符串text1[0:i)和text2[0:j)之间能构成的最大子序列，结果返回dp[len1][len2]
//         状态转移方程：对于当前遍历的字符text1[i-1]和text2[j-1]，有2中可能情况：
//                 1）text1[i-1]==text2[j-1]，那么dp[i][j]取决于加入这个字符前的最大值dp[i-1][j-1]。
//                 2）text1[i-1]!=text2[j-1]，说明text1[i-1]或ext2[j-1]的加入对于dp[i][j]没有影响，dp[i][j]
//                     取决于dp[i-1][j]和dp[i][j-1]的较大值。
//         初始值：无特殊初始值
// 时间复杂度：O(len1 * len2), len1 = text1.length(), len2 = text2.length()
// 空间复杂度：O(len1 * len2), len1 = text1.length(), len2 = text2.length()

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = text1.charAt(i - 1), c2 = text2.charAt(j - 1);
                dp[i][j] = c1 == c2 ? dp[i - 1][j - 1] + 1: Math.max(dp[i - 1][j],  dp[i][j - 1]);
            }
        }
        
        return dp[len1][len2];
    }
}


follow-up questions: 
1）输出任意一个LCS。在dp求长度的同时，维护变量String s，当遇到c1 == c2时，添加到s末尾。
2）输出所有LCS。参考：https://songlee24.github.io/2014/11/29/print-all-LCS/