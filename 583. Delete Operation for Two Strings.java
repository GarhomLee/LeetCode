https://leetcode.com/problems/delete-operation-for-two-strings/

// 总体思路：72. Edit Distance的简化版，只考虑删除操作，仍然是DP问题。

// 解法一：借助longest common subsequence（LeetCode中没有单独的相关题目）
//        先用DP找到两个字符串的longest common subsequence，然后两个字符串总长度减去longest common subsequence
//        的长度就是要删除的字符的数量。


// 解法二：直接考虑要删除的字符数量，Bottom-up DP
//        状态函数：1-based二维dp数组，dp[i][j]表示要使得word1[0:i)和word2[0:j)变为相同的字符串所需的最小删除操作数。
//        状态转移方程：对于当前遍历到的word1[i]和word2[j]（为方便理解记为0-based），有两种情况：
//               1）word1[i]==word2[j]，字符匹配，不需要删除，所以dp[i][j] = dp[i - 1][j - 1];
//               2）word1[i]!=word2[j]，字符不匹配，要么删除word1[i]，要么删除word2[j]，因此需要从dp[i - 1][j]和
//                  dp[i][j - 1]中取较小值，再+1表示当前的删除操作。
//        初始值：当word1.length()==0时，dp值为word2.length()，即dp[0][j] = j；
//               同理，当word2.length()==0时，dp值为word1.length()，即dp[i][0] = i；

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
            for (int j = 1; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}