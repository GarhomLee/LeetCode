https://leetcode.com/problems/consecutive-characters/

idea: Use variables to hold the previous char as well as its consecutive length.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxPower(String s) {
        int maxLen = 0, currLen = 0;
        char pre = '.';
        for (char c : s.toCharArray()) {
            if (c != pre) {
                currLen = 1;
                pre = c;
            } else {
                currLen++;
            }
            
            maxLen = Math.max(maxLen, currLen);
        }
        
        return maxLen;
    }
}