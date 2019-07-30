https://leetcode.com/problems/unique-substrings-in-wraparound-string/

// 思路：DP，本质上是求最长连续子串的长度。
//         如果以字母i结尾的最长子串长度为x，那么【以之为结尾能形成的子串个数也为x】。因此，这道题转化成了以每个
//         字母为结尾的最长连续字母子串长度，然后对它们求和。
//         状态函数：dp[i]，表示'a'到'z'的某字母为结尾的最长子串长度。如果没有出现过该字母，dp[i]=0
//         状态转移方程：维护变量currLen，表示当前的连续字母子串长度。如果当前字母是前一个字母在字母表中的下一位，或者
//                 'z'紧接'a'，那么currLen++；否则，currLen重置为1。实际上，currLen是降维后的另一个dp数组。
//                 对于每个p中的字母，利用currLen更新dp[i]。
//         初始值：dp[i]无特殊初始值；currLen初始值为1
//         最后将所有dp值累加到res中，返回res。

class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) return 0; 
        
        int[] dp = new int[26];
        int currLen = 1;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) == p.charAt(i - 1) + 1 || (p.charAt(i) == 'a' && p.charAt(i - 1) == 'z'))) {  // consecutive substring found
                currLen++;
            } else {  // not in a consecutive substring
                currLen = 1;  // reset
            }
            /* update dp[i] */
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], currLen);
        }
        
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res += dp[i];
        }
        return res;
    }
}