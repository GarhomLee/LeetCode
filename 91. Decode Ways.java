https://leetcode.com/problems/decode-ways/

// 解法一：Backtracking，把String分段

// 解法二：对Backtracking做优化，发现实际上可以优化成DP问题，因为实际上只和当前digit以及和前一个digit的组合有关。
//     如果当前i的digit（即num1）在1到9之间（即不为0），可以看作把i插入[0 ... i - 1]的substring末尾，可以解码的方法和[0 ... i - 1]的substring相同；
//     如果i - 1和i的组合（即num2）在10到26之间，可以看作把num2插入[0 ... i - 2]的substring末尾，可以解码的方法和[0 ... i - 2]的substring相同；
//     所以总共可以解码的方法dp[i]为dp[i - 1] + dp[i - 2]，当num1和num2都valid时。

class Solution {
    int count = 0;
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;  // corner cases: leading '0' is always not decodable

        int[] dp = new int[s.length() + 1];  // dp[i] indicates the ways to decode substring from position 0 to i
        dp[0] = 1;  // initialize
        dp[1] = 1;  // there is only 1 way if only 1 letter
        for (int i = 2; i <= s.length(); i++) {
            int num1 = Integer.parseInt(s.substring(i - 1, i)), num2 = Integer.parseInt(s.substring(i - 2, i));  // get numbers from current first and first two char
            if (num1 >= 1 && num1 <= 9) dp[i] = dp[i - 1];
            if (num2 >= 10 && num2 <= 26) dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }
}