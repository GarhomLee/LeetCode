https://leetcode.com/problems/count-binary-substrings/

solution1: Info Cache. Refer to: https://leetcode.com/problems/count-binary-substrings/discuss/108625/JavaC%2B%2BPython-Easy-and-Concise-with-Explanation
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int countBinarySubstrings(String s) {
        int curr = 1, prev = 0, res = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                res += Math.min(curr, prev);
                prev = curr;
                curr = 1;
            } else {
                curr++;
            }
        }
        
        return res;
    }
}


solution2: Brute Force
time complexity: O(n^2)
space complexity: O(1)

class Solution {
    private int helper(String s, int left, char leftChar, int right, char rightChar) {
        if (leftChar == rightChar) {
            return 0;
        }
        
        int count = 0;
        while (left >= 0 && right < s.length() 
               && s.charAt(left) == leftChar
               && s.charAt(right) == rightChar) {
            left--;
            right++;
            count++;
        }
        
        return count;
    }
    
    public int countBinarySubstrings(String s) {
        int res = 0;
        for (int i = 0; i + 1 < s.length(); i++) {
            res += helper(s, i, s.charAt(i), i + 1, s.charAt(i + 1));
        }
        return res;
    }
}