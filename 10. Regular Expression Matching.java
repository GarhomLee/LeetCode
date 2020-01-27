https://leetcode.com/problems/regular-expression-matching/solution/

// 思路：和44. Wildcard Matching类似，但'*'的作用不相同。在这题中，'*'表示可以匹配0个和多个【'*'的前一个字符】，这意味着
//     只有当'*'前为字母或者'.'时才能匹配，连续两个'*'永远无法匹配。
//     这题是两个字符串匹配的问题，可以使用DP。维护二维boolean的dp数组（1-based），dp[i][j]表示p[0:j - 1]能否匹配s[0:i - 1]，dp[0][j]表示s为空，dp[i][0]表示p为空。
//     可以分为以下几种情况：
//     （1）s和p都为空，这时匹配结果为true。
//     （2）p为空，匹配结果为false。参考在Word中Ctrl+F搜索框中什么也不输入的情况。
//     （3）s为空，只有当出现'*'且不是连续的'*'时，意味着'*'和它前一个字符组成【可匹配的'*'组合】，因为s为空，所以【这个组合】只能代表匹配0个'*'的前一个字符，
//         所以需要取决于【这个组合】之前的情况，即取决于dp[i][j - 2]的情况。特殊情况，j == 1，不论p有什么字符都是false。
//     （4）s和p都不为空，且p[j]和s[i]匹配，或者p[j]为'.'可以和任意s[i]匹配，所以dp[i][j]取决于dp[i - 1][j - 1]
//     （5）s和p都不为空，且p[j]为'*'。这种情况下，又可以分为以下几种细分情况：
//         （a）'*'为p的第一个字符，或者连续出现两个'*'，为false
//         （b）'*'之前的字符p[j - 1]和s的最后一个字符s[i]匹配，或者p[j - 1]为'.'可以和任意s[i]匹配，说明：要么当p[0:j]能和s[0:i - 1]匹配，这个'*'可能发挥了作用，接着匹配s[i]，
//             （即dp[i][j]取决于dp[i - 1][j]）；要么'*'和它前一个字符组成【可匹配的'*'组合】匹配0个'*'的前一个字符，p[0:j]和p[0:j - 2]效果一样（即dp[i][j]取决于dp[i][j - 2]）。
//             所以dp[i][j]取决于dp[i - 1][j]或dp[i][j - 2]，当任意一种情况成立时'*'就可以发挥作用。
//         （c）其他情况下，即'*'之前的字符p[j - 1]和s的最后一个字符s[i]不匹配，那么'*'和它前一个字符组成【可匹配的'*'组合】一定没有作用，匹配了0个'*'的前一个字符，
//             p[0:j]和p[0:j - 2]效果一样，所以dp[i][j]取决于dp[i][j - 2]。

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];  // dp[i][j] indicates whether p[0:j - 1] matches s[0:i - 1]
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 && j == 0) {  // both s and p are empty, it's true
                    dp[i][j] = true;  
                }
                else if (j == 0) {  // p is empty
                    dp[i][j] = false;
                }
                else if (i == 0) {
                    dp[i][j] = j >= 2 && p.charAt(j - 1) == '*' && p.charAt(j - 2) != '*' && dp[i][j - 2];
                }
                else if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {  // matches the same char or there is a '.' at p
                    dp[i][j] = dp[i - 1][j - 1];  
                }
                else if (p.charAt(j - 1) == '*') {
                    if (j == 1 || p.charAt(j - 2) == '*') dp[i][j] = false;
                    else if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    else dp[i][j] = dp[i][j - 2];
                }
            }
        }
        
        return dp[s.length()][p.length()];
    }
}


二刷：代码精简
时间复杂度：O(slen + plen)
空间复杂度：O(slen + plen)

class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];

        // initial values
        dp[0][0] = true;
        for (int pi = 1; pi <= plen; pi++) {
            dp[0][pi] = pi >= 2 && p.charAt(pi - 1) == '*' && p.charAt(pi - 2) != '*' && dp[0][pi - 2];
        }
        
        // state transfer
        for (int si = 1; si <= slen; si++) {
            for (int pi = 1; pi <= plen; pi++) {
                if (p.charAt(pi - 1) == s.charAt(si - 1) || p.charAt(pi - 1) == '.') {
                    dp[si][pi] = dp[si - 1][pi - 1];
                } else if (pi >= 2 && p.charAt(pi - 1) == '*' && p.charAt(pi - 2) != '*') {
                    dp[si][pi] = dp[si][pi - 2];
                    if (p.charAt(pi - 2) == s.charAt(si - 1) || p.charAt(pi - 2) == '.') {
                        dp[si][pi] = dp[si][pi] || dp[si - 1][pi];
                    }
                }
            }
        }
        
        return dp[slen][plen];
    }
}