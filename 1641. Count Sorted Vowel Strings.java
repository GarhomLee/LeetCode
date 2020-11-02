https://leetcode.com/problems/count-sorted-vowel-strings/

idea: DP
time complexity: O(n*5)=O(n)
space complexity: O(5)

class Solution {
    public int countVowelStrings(int n) {
        int[] dp = new int[5];  // dp[j] indicates, for given length i, the number of 
                                // non-decreasing string with the first char j={'a','e','i','o','u'}
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 3; j >= 0; j--) {
                dp[j] += dp[j+1];
            }
        }
        return Arrays.stream(dp).sum();
    }
}