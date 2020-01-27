https://leetcode.com/problems/wildcard-matching/

// 思路：和10. Regular Expression Matching类似，但'*'的作用不相同。在这题中，'*'表示可以匹配任意连续的0个至多个字符。

// 解法一：DP
//         维护二维boolean的dp数组（1-based），dp[i]][j]表示s[0:i - 1]和p[0:j - 1]是否匹配，dp[0][j]表示s为空，dp[i][0]表示p为空。
//         对于s[i]和p[j]，如果s[i] == p[j]或者p[j]为匹配任意一个字符的'?'，那么只需看s[0:i - 1]是否和p[0:j - 1]全部匹配。
//         如果不是以上的情况，但p[j]为匹配0至多个字符的'*'，那么当以下三种情况任一满足即为true：
//         （1）s[0:i]是否和p[0:j - 1]全部匹配，即加入的'*'匹配了0个字符；
//         （2）s[0:i - 1]是否和p[0:j]全部匹配，即'*'至少匹配了s[i - 1]；
//         （3）s[0:i - 1]是否和p[0:j - 1]全部匹配，即'*'至少匹配了s[i]
//         实际上，（3）可以合并到（2）中，所以dp[i][j]只和dp[i][j - 1]，dp[i - 1][j]有关。

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {  // String s is empty, if there is a '*' in p, dp[i][j] depends on dp[i][j - 1]
                    dp[i][j] = p.charAt(j - 1) == '*' && dp[i][j - 1];
                } else if (j == 0) {  // String p is empty, not possible to match
                    dp[i][j] = false;
                } else {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j] || dp[i - 1][j - 1];
                    }
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
            dp[0][pi] = p.charAt(pi - 1) == '*' && dp[0][pi - 1];
        }
        
        // state transfer
        for (int si = 1; si <= slen; si++) {
            for (int pi = 1; pi <= plen; pi++) {
                if (s.charAt(si - 1) == p.charAt(pi - 1) || p.charAt(pi - 1) == '?') {
                    dp[si][pi] = dp[si - 1][pi - 1];
                } else if (p.charAt(pi - 1) == '*') {
                    dp[si][pi] = dp[si][pi - 1] || dp[si - 1][pi];
                }
            }
        }
        
        return dp[slen][plen];
    }
}


// 解法二：Two pointers，详见https://leetcode.wang/leetCode-44-Wildcard-Matching.html

class Solution {
    boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;     
        //遍历整个字符串
        while (s < str.length()){
            // 一对一匹配，两指针同时后移。
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // 碰到 *，假设它匹配空串，并且用 startIdx 记录 * 的位置，记录当前字符串的位置，p 后移
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // 当前字符不匹配，并且也没有 *，回退
            // p 回到 * 的下一个位置
            // match 更新到下一个位置
            // s 回到更新后的 match 
            // 这步代表用 * 匹配了一个字符
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //字符不匹配，也没有 *，返回 false
            else return false;
        }

        //将末尾多余的 * 直接匹配空串 例如 text = ab, pattern = a*******
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
}