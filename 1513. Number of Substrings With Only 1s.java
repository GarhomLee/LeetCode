https://leetcode.com/problems/number-of-substrings-with-only-1s/

idea: Count
time complexity: O(n)
space complexity: O(1)

class Solution {
    final int MOD = 1_000_000_007;
    public int numSub(String s) {
        int ret = 0, curr = 0;
        for (char c : s.toCharArray()) {
            curr = c == '1' ? curr + 1 : 0;
            ret += curr;
            ret %= MOD;
        }
        
        return ret;
    }
}