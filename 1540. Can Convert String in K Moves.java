https://leetcode.com/problems/can-convert-string-in-k-moves/

idea: Greedy
    -Count each possible forward difference between two chars.
    -Since each step i can only be used in one difference, we can check if k is greater than
     the maximum needed difference for all diff.
time complexity: O(n + 26)
space complexity: O(26)

class Solution {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int n = s.length();
        int[] moves = new int[26];
        for (int i = 0; i < n; i++) {
            int diff = (t.charAt(i) - s.charAt(i) + 26) % 26;
            moves[diff]++;
        }
        
        for (int diff = 1; diff < 26; diff++) {
            if (moves[diff] > 0 && diff + 26 * (moves[diff] - 1) > k) {
                return false;
            }   
        }
        
        return true;
    }
}