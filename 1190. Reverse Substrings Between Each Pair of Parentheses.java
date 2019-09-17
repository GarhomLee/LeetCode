https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/

思路：Recursion

class Solution {
    public String reverseParentheses(String s) {
        return dfs(s, false);
    }
    
    private String dfs(String s, boolean hasBracket) {
        int leftBracketIndex = s.indexOf('(');

        StringBuilder sb = new StringBuilder();
        if (leftBracketIndex < 0) {
            sb.append(s);
        } else {
            int oldIndex = 0;
            while (leftBracketIndex >= 0) {
                sb.append(s.substring(oldIndex, leftBracketIndex));
                int rightBracketIndex = leftBracketIndex + 1, leftBracketCount = 1;
                while (rightBracketIndex < s.length() && leftBracketCount > 0) {
                    char c = s.charAt(rightBracketIndex);
                    if (c == ')') {
                        leftBracketCount--;
                    } else if (c == '(') {
                        leftBracketCount++;
                    }
                    rightBracketIndex++;
                }

                rightBracketIndex--;
                sb.append(dfs(s.substring(leftBracketIndex + 1, rightBracketIndex), true));
                oldIndex = rightBracketIndex + 1;
                leftBracketIndex = s.indexOf('(', oldIndex);
            }
            
            sb.append(s.substring(oldIndex));
        }
        
        
        return hasBracket ? sb.reverse().toString() : sb.toString();
    }
}