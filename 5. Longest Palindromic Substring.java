https://leetcode.com/problems/longest-palindromic-substring/

// 解法一：递归，中心扩散。
//         利用helper method，以当前位置i（奇数palindrome）或i和i+1（偶数palindrome）为中心，向两边扩散寻找局部最长palindrome。

class Solution {
    int maxLen = 0;  // the length of possible longest palindromic substring
    String maxSubstring = "";  // the possible longest palindromic substring
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;  // corner cases
        
        
        for (int i = 0; i < s.length(); i++) {
            findLongestPalindrome(s, i, i);  // palindrome with odd number
            findLongestPalindrome(s, i, i + 1);  // palindrome with even number
        }
        return maxSubstring;
    }
    
    private void findLongestPalindrome(String s, int left, int right) {
        if (left < 0 || right >= s.length()) return;  // boundary
            
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // when exit the loop, s[left] and s[right] are NOT in the palindrome substring
        if (right - left - 1 > maxLen) {
            maxLen = right - left - 1;
            maxSubstring = s.substring(left + 1, right);
        }
    }
}

// 解法二：DP。维护二维boolean的dp数组（0-based），dp[left][right]表示s[left:right]是否为palindrome。注意left一定小于等于right，所以dp为上三角矩阵。
//         外层循环：right右移；内层循环：left从0到right。
//         dp[left][right]为true（即s[left:right]为palindrome）的条件是：s[left] == s[right]，且dp[left + 1][right - 1]为true（即s[left + 1:right - 1]为palindrome）。
//         dp[left + 1][right - 1]存在的前提是right > left + 2，所以意味着，当right <= left + 2时一定为palindrome，不需要用到dp[left + 1][right - 1]。
//         如果dp[left][right]为true，那么需要更新maxLen和maxSubstring。

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        
        boolean[][] dp = new boolean[s.length()][s.length()];  // dp[left][right] indicates if s[left:right] is palindrome
        int maxLen = 0;  // the length of possible longest palindromic substring
        String maxSubstring = "";  // the possible longest palindromic substring
        
        for (int right = 0; right < s.length(); right++) {
            for (int left = 0; left <= right; left++) {
                dp[left][right] = s.charAt(left) == s.charAt(right) && (right <= left + 2 || dp[left + 1][right - 1]);
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    maxSubstring = s.substring(left, right + 1);
                }
            }
        }
        return maxSubstring;
    }
}