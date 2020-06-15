https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

idea: Greedy
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minAddToMakeValid(String S) {
        int left = 0, res = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (left == 0) {
                res++;
            } else {
                left--;
            }
        }
        
        return res + left;
    }
}