https://leetcode.com/problems/longest-palindromic-subsequence/

// 思路：DP，转化为求s和翻转字符串s.reverse()的longest common subsequence
//         状态函数：dp[i][j]表示s[0:i)和reverses[0:j)的拥有的longest common subsequence
//         状态转移方程：比较s[i]和reverse[j]，有两种情况：
//                 如果s[i]==reverse[j]，那么lcs长度相对于没放这个字符前的长度+1，即dp[i][j] = dp[i - 1][j - 1] + 1；
//                 如果s[i]!=reverse[j]，那么dp[i][j]取加入s[i]前的长度或者加入reverse[j]前的长度的较大值，即dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
//         初始值：无特殊初始值
// 注意：从翻转字符串s.reverse()得到第j-1个字符，也就是从原字符串s得到第s.length() - j个字符

class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(i - 1) == s.charAt(s.length() - j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[s.length()][s.length()];
    }
}