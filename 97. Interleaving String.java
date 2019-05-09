https://leetcode.com/problems/interleaving-string/

// 总体思路：有关两个String的DP问题，要用二维DP数组。这个问题比较特殊，用到【boolean的二维数组】。
//         观察规律可知，s3[i + j + 1]可以来则s1[i]【或者】s2[j]。如果s1[i] == s3[i + j + 1]，同时也满足s1[0:i - 1]和s2[0:j]能组成s3[0:i + j]，
//         说明s1[0:i]和s2[0:j]能组成s3[0:i + j + 1]；同理对于s2[j]，如果s2[j] == s3[i + j + 1]，同时也满足s1[0:i]和s2[0:j - 1]能组成s3[0:i + j]，
//         说明s1[0:i]和s2[0:j]能组成s3[0:i + j + 1]；因此，只要这两种情况满足其中一种，当前的dp值就为true。
//         特殊情况：当s1为空，只需考察s2是否和s3一一对应；s2为空时同理。
//         注意：【dp数组的index和String的index有差异】，off by one。
//         时间复杂度：O(s1.length() * s2.length())；空间复杂度：O(s1.length() * s2.length())

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;  // corner case: if the length does not match, just return false
        
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];  // dp[i][j] indicates whether or not interleaving s1[0:i - 1] and s2[0:j - 1] can form s3[0:i + j - 1]

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) dp[i][j] = true;  // empty s1 and empty s2 can always form empty s3
                else if (i == 0) dp[i][j] = s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1];  // s1 is empty, s3 is formed only by s2
                else if (j == 0) dp[i][j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j];  // s2 is empty, s3 is formed only by s1
                else dp[i][j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) 
                    || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

// 优化：由于观察可知dp[i][j]只和dp[i - 1][j]和dp[i][j - 1]即上面和左边的值有关，所以可以使用滚动数组把空间复杂度降为O(s2.length())

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) dp[j] = true;
                else if (i == 0) dp[j] = s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1];
                else if (j == 0) dp[j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j];
                else dp[j] = (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j]) 
                    || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1]);
            }
        }
        return dp[s2.length()];
    }
}