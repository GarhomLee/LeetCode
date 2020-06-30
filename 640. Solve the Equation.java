https://leetcode.com/problems/solve-the-equation/

idea: Parse String
time complexity: O(n)
space complexity: O(1)

class Solution {
    private int[] getRes(String s) {
        int[] res = new int[2];
        int left = 0;
        while (left < s.length()) {
            int right = left;
            int sign = 1;
            Integer curr = null;
            if (s.charAt(left) == '+' || s.charAt(left) == '-') {
                sign = s.charAt(left) == '+' ? 1 : -1;
                right++;
            }
            
            while (right < s.length() && Character.isDigit(s.charAt(right))) {
                if (curr == null) {
                    curr = 0;
                }
                curr = curr * 10 + (s.charAt(right++) - '0');
            }
            
            if (right < s.length() && s.charAt(right) == 'x') {
                if (curr == null) {
                    curr = 1;
                }
                res[0] += sign * curr;
                right++;
            } else {
                res[1] += sign * curr;
            }
            
            left = right;
        }
        
        return res;
    }
    
    public String solveEquation(String equation) {
        equation = equation.replace("\\s+", "");
        String[] split = equation.split("=");
        int[] leftRes = getRes(split[0]), rightRes = getRes(split[1]);
        if (leftRes[0] == rightRes[0]) {
            return leftRes[1] == rightRes[1] ? "Infinite solutions" : "No solution";
        }
        
        return "x=" + ((rightRes[1] - leftRes[1]) / (leftRes[0] - rightRes[0]));
    }
}