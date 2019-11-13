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