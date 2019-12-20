https://leetcode.com/problems/maximum-score-words-formed-by-letters/

思路：Backtracking
        递归函数定义：
        终止条件：
        递归过程：
时间复杂度：O(w! * l), w=words.length, l=words[i].length
空间复杂度：O(w), w=words.length

class Solution {
    int max = 0;
    
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int len = words.length;
        if (len == 0) {
            return 0;
        }
        
        int[] charCount = new int[26];
        for (char c : letters) {
            charCount[c - 'a']++;
        }
        
        dfs(words, 0, 0, charCount, score);
        
        return max;
    }
    
    private void dfs(String[] words, int index, int totalScore, int[] charCount, int[] score) {
        // goal
        if (index == words.length) {
            max = Math.max(max, totalScore);
            return;
        }
        
        // choices
        for (int i = index; i < words.length; i++) {
            boolean isValid = true;
            int currScore = 0;
            for (char c : words[i].toCharArray()) {
                currScore += score[c - 'a'];
                --charCount[c - 'a'];
                if (charCount[c - 'a'] < 0) {
                    isValid = false;
                }
            }

            // constraint
            if (!isValid) {
                currScore = 0;
            }
            
            dfs(words, i + 1, totalScore + currScore, charCount, score);

            // reset
            for (char c : words[i].toCharArray()) {
                charCount[c - 'a']++;
            }
        }
    }
}


再刷：DP + Bit Manipulation，对比1066. Campus Bikes II
时间复杂度：O(2^n), n=wordslength
空间复杂度：O(2^n), n=wordslength

class Solution {
    /* find the maximum score given the info of unusedWords */
    private int dfs(int start, String[] words, int unusedWords, int[] count, int[] score, int[] dp) {
        if (start == words.length) {
            return 0;
        }
        if (dp[unusedWords] != 0) {
            // cached results
            return dp[unusedWords];
        }
        
        for (int i = start; i < words.length; i++) {
            boolean isValid = true;
            int currScore = 0;
            for (char c : words[i].toCharArray()) {
                count[c]--;
                currScore += score[c - 'a'];
                if (count[c] < 0) {
                    isValid = false;
                }
            }
            
            if (isValid) {
                dp[unusedWords] = Math.max(dp[unusedWords], currScore + dfs(i + 1, words, unusedWords | (1 << i), count, score, dp));
            }
            
            for (char c : words[i].toCharArray()) {
                count[c]++;
            }
        }
        
        return dp[unusedWords];
    }
    
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] dp = new int[1 << words.length];
        int[] count = new int[128];
        for (char c : letters) {
            count[c]++;
        } 

        return dfs(0, words, 0, count, score, dp);
    }
}