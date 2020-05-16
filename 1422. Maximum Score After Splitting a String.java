https://leetcode.com/problems/maximum-score-after-splitting-a-string/

idea: Prefix Sum
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxScore(String s) {
        int n = s.length();
        int[] zeroCount = new int[n + 1], oneCount = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            oneCount[i] = oneCount[i - 1] + (c == '1' ? 1 : 0);
            zeroCount[i] = zeroCount[i - 1] + (c == '0' ? 1 : 0);
        }
        
        int maxScore = 0;
        for (int i = 1; i < n; i++) {
            int score = zeroCount[i] + (oneCount[n] - oneCount[i]);
            maxScore = Math.max(maxScore, score);
        }
        
        return maxScore;
    }
}