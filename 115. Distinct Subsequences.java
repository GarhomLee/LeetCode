https://leetcode.com/problems/distinct-subsequences/

// 总体思路：DP问题，需要使用二维dp数组。
//         观察可以发现，对于t[0:i]，s[j]加入到s[0:j - 1]后的新得到distinct subsequences数的distinct subsequences数和当前的t[i]有关。
//         如果t[i] != s[j]，说明s[j]对于distinct subsequences数的增加没有贡献，所以s[0:j]和s[0:j - 1]一样，所以dp[i][j] = dp[i][j - 1]。
//         如果t[i] == s[j]，说明包含着t[0:i - 1]的s[0:j - 1]都对包含t[0:i]的s[0:j]的distinct subsequences数有贡献，同时包含t[0:i]的s[0:j - 1]也能产生贡献，
//         即dp[i][j]来源于dp[i][j - 1] + dp[i - 1][j - 1]。
//         注意当t为空的时候dp都为1；注意【dp数组的index和String的index的差异】。
//         时间复杂度：O(s1.length() * s2.length())；空间复杂度：O(s1.length() * s2.length())

class Solution {
    public int numDistinct(String s, String t) {
        if (s.length() == 0 || s.length() < t.length()) return 0;
        
        int[][] dp = new int[t.length() + 1][s.length() + 1];  // dp[i][j] indicates the number of distinct subsequences of S substring from 1 to j (1-based) which equals T substring from 1 to i (1-based)
        Arrays.fill(dp[0], 1);  // when T is empty, the number of distinct subsequences of S which equals T is always be 1
        
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j - 1) != t.charAt(i - 1)) dp[i][j] = dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
            }
        }
        return dp[t.length()][s.length()];
    }
}