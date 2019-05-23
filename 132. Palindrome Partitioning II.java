https://leetcode.com/problems/palindrome-partitioning-ii/

// 总体思路：Bottom-up DP。
//         状态函数：dp[i]表示将s[0:i)切分为所有substring都是palindrome的最小切分次数。当s[0:i)本身为palindrome时，最小切分次数为0
//         状态转移方程：对于s[0:i)中间的某个位置k，如果s[k:i)为palindrome，那么需要在k处切一次（即dp[k]+1），所以dp[i]是所有可能的位置k中取最小值。
//                 特殊情况：k==0，即s[0:i)整个为palindrome，那么dp[i]=0
//         初始值：dp[right]初始化为某较大数，如s.length() + 10
// 时间复杂度：O(n^3)
// 空间复杂度：O(n)

class Solution {
    public int minCut(String s) {
        int[] dp = new int[s.length() + 1];
        
        for (int right = 1; right <= s.length(); right++) {
            dp[right] = s.length() + 10;
            for (int left = 0; left < right; left++) {
                String substring = s.substring(left, right);
                if (isPalindrome(substring)) {
                    dp[right] = left == 0 ? 0 : Math.min(dp[right], dp[left] + 1);
                }
            }
        }
        return dp[s.length()];
    }
    
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}

// 优化：实际上，在判断substring是否为palindrome的过程中，进行了大量重复计算，如求isPalindrome(s[i:j))，可以由s[i]==s[j-1] && isPalindrome(s[i+1:j-1))
//     得到，所以对于isPalindrome(s[i:j))也可以用memoization来优化时间复杂度。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public int minCut(String s) {
        int[] dp = new int[s.length() + 1];
        boolean[][] palindrome = new boolean[s.length() + 1][s.length() + 1];  // optimization
        
        for (int right = 1; right <= s.length(); right++) {
            dp[right] = s.length() + 10;
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right - 1) && (right - 1 - left <= 2 || palindrome[left + 1][right - 1])) {
                    palindrome[left][right] = true;
                    dp[right] = left == 0 ? 0 : Math.min(dp[right], dp[left] + 1);
                }
            }
        }
        return dp[s.length()];
    }
}