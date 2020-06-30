https://leetcode.com/problems/longest-repeating-substring/

idea: DP
time complexity: O(n^2)
space complexity: O(n^2)

class Solution {
    public int longestRepeatingSubstring(String S) {
        if(S == null || S.length() == 0){
           return 0;
        }
       
        int n = S.length(), res = 0;
       
        int [][] dp = new int[n+1][n+1];
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<i; j++){
                if(S.charAt(i-1) == S.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    res = Math.max(res, dp[i][j]);            
               }
            }
        }
       
        return res;
    }
}