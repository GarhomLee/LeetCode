https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/

idea: Count maximum left parentheses
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxDepth(String s) {
        int left = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                left--;
            }
            max = Math.max(max, left);
        }
        
        return max;
    }
}