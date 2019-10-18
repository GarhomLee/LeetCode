https://leetcode.com/problems/word-break/

// 总体思路：Bottom-up Memoization
//         状态函数：dp[i]表示s[0:i)是否能切分成包含dictionary里所有单词的sequence（1-based简化代码）
//         状态转移方程：将s[0:i)在k处分为两个左右部分，如果s[0:k)能切分成包含dictionary里所有单词的sequence（即dp[k]==true），同时s[k:i)在dictionary中，
//                 那么说明s[0:i)是否能切分成包含dictionary里所有单词的sequence（即dp[i]==true）。只需存在至少1个这样的位置k即可。
//         初始值：dp[0] = true，本身无意义
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];  // dp[i] indicates whether s[0:i-1] can be validly segemented
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int right = 1; right <= s.length(); right++) {
            for (int left = 0; left < right; left++) {
                /* 第一种可行的状态转移方程 */
                //if (dp[left]) dp[right] = dp[right] || set.contains(s.substring(left, right));

                /* 第二种可行的状态转移方程 */
                if (dp[left] && set.contains(s.substring(left, right))) {
                    dp[right] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}


// 解法二：Top-down Recursion with Memoization
// 时间复杂度：O(w^2), w=wordDict.size()
// 空间复杂度：O(n), n=s.length()

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Set<String> set =  new HashSet<>(wordDict);
        int[] dp = new int[s.length()];
        return dfs(s, 0, wordDict, dp) == 2;
    }
    
    private int dfs(String s, int start, List<String> list, int[] dp) {
        if (start >= s.length()) {
            return start == s.length() ? 2 : 1;
        }
        if (dp[start] != 0) {
            return dp[start];
        }
        
        dp[start] = 1;
        for (String next: list) {
            if (s.startsWith(next, start)) {
                dp[start] = dfs(s, start + next.length(), list, dp);
                if (dp[start] == 2) {
                    break;
                }
            }
        }
        
        return dp[start];
    }
}