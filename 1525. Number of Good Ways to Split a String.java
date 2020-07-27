https://leetcode.com/problems/number-of-good-ways-to-split-a-string/

idea: HashMap (mimicked by array) (+ Sliding Window)
time complexity: O(n)
space complexity: O(26)

class Solution {
    public int numSplits(String s) {
        int[] countLeft = new int[26], countRight = new int[26];
        int distinctLeft = 0, distinctRight = 0;
        for (char c : s.toCharArray()) {
            if (countRight[c - 'a'] == 0) {
                distinctRight++;
            }
            countRight[c - 'a']++;
        }
        
        int ret = 0;
        for (char c : s.toCharArray()) {
            if (countLeft[c - 'a'] == 0) {
                distinctLeft++;
            }
            countLeft[c - 'a']++;
            
            countRight[c - 'a']--;
            if (countRight[c - 'a'] == 0) {
                distinctRight--;
            }
            
            if (distinctLeft == distinctRight) {
                ret++;
            }
        }
        
        return ret;
    }
}