https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/

idea: Refer to: https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/discuss/780199/JavaC%2B%2BPython-Straight-Forward-One-Pass
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minInsertions(String s) {
        int right = 0, ret = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (right % 2 != 0) {
                    ret++;
                    right--;
                }
                right += 2;
            } else {
                right--;
                if (right < 0) {
                    ret++;
                    right += 2;
                }
            }
        }
        
        return right + ret;
    }
}