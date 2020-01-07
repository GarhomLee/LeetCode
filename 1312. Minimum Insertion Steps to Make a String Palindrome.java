https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

思路：Recursion with Memoization
        递归函数定义：int dfs(String s, int left, int right, int[][] dp)表示s[left:right]
            要形成palindrome所需的最少插入字符数。辅助数组dp[left][right]定义相同，初始化为-1。
        终止条件：1)left >= right，直接返回0.
                2）dp[left][right] != -1，说明s[left:right]已经搜索过，返回dp[left][right]
        递归过程：分两种情情况：
                1）s[left] == s[right]，两个字符可以抵消，不需要增加字符，因此结果和s[left+1:right-1]相同。
                2）s[left] != s[right]，取s[left+1:right]在右边增加s[left]和s[left:right-1]在左边
                    增加s[right]的较小值，再+1表示增加的字符。
时间复杂度：O(len^2)
空间复杂度：O(len^2)

class Solution {
    private int dfs(String s, int left, int right, int[][] dp) {
        if (left >= right) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        
        char cl = s.charAt(left), cr = s.charAt(right);
        if (cl == cr) {
            dp[left][right] = dfs(s, left + 1, right - 1, dp);
        } else {
            dp[left][right] = 1 + Math.min(dfs(s, left + 1, right, dp), dfs(s, left, right - 1, dp));
        }
        
        return dp[left][right];
    }
    
    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return dfs(s, 0, len - 1, dp);
    }
}


解法二：DP
        状态函数定义：dp[left][right]表示s[left:right]要形成palindrome所需的最少插入字符数。最后结果
            返回dp[0][len - 1]。
        初始值：dp[left][right] = 0
        状态转移方程：分两种情情况：
            1）s[left] == s[right]，两个字符可以抵消，不需要增加字符，因此结果和s[left+1:right-1]相同。
            2）s[left] != s[right]，取s[left+1:right]在右边增加s[left]和s[left:right-1]在左边
                增加s[right]的较小值，再+1表示增加的字符。
时间复杂度：O(len^2)
空间复杂度：O(len^2)

class Solution {
    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = right - 1; left >= 0; left--) {
                char cl = s.charAt(left), cr = s.charAt(right);
                if (cl == cr) {
                    dp[left][right] = left == right - 1 ? 0 : dp[left + 1][right - 1];
                } else {
                    dp[left][right] = 1 + Math.min(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
        
        return dp[0][len - 1];
    }
}