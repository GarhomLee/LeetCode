https://leetcode.com/problems/is-subsequence/

// 总体思路：虽然也是两个字符串的问题，但DP解法【不一定更快】。

// 解法一：Two pointers。两个指针分别在s和t扫描，当s[si] == t[ti]时，si右移一位。只有si == s.length()时才为true。
// 时间复杂度：O(m + n)，m = s.length()，n = t.length()。当t很大时，运行速度比DP解法快。
// 空间复杂度：O(1)
// 犯错点：1.当s为空时，s[0]过界，所以需要在corner case中进行判断。

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;  // {Correction 1}
        if (t.length() == 0 && s.length() != 0) return false;
        
        for (int si = 0, ti = 0; ti < t.length(); ti++) {  // {Mistake 1: when si == 0, if s is empty, it would be out of range. Correction 1: add corner case}
            if (s.charAt(si) == t.charAt(ti)) si++;
            if (si == s.length()) return true;
        }
        return false;
    }
}

// 解法二：DP，维护二维boolean的dp数组（1-based）。状态函数dp[i][j]表示s[0:i - 1]是否为t[0:j - 1]的subsequence；
//         状态转移方程：dp[i][j]取决于s[i]和t[j]，如果s[i] == t[j]，有可能成为新的subsequence，dp[i][j] = dp[i - 1][j - 1]；否则，t[j]不影响结果，dp[i][j] = dp[i][j - 1]。
// 时间复杂度：O(m * n)，m = s.length()，n = t.length()。当t很大时，运行速度很慢。
// 空间复杂度：O(m * n)，m = s.length()，n = t.length()

class Solution {
    public boolean isSubsequence(String s, String t) {
        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];  // dp[i][j] indicates whether s[0:i - 1] is subsequence of t[0:j - 1]
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (i == 0) dp[i][j] = true;  // when s is empty, s is always a subsequence of t
                else if (j == 0) dp[i][j] = false;  // when s is not empty but t is empty, s would never be a subsequence of t
                else  dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i][j - 1];
            }
        }
        return dp[s.length()][t.length()];
    }
}

