https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

idea: Stack + HashSet
        1st pass: use a HashSet to identify and mark the char idx that should be removed.
        2nd pass: build a new string with removed indexes.
time comp: O(n)
space comp: O(n)

class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new LinkedList<>();  // position of '(' to be determined
        Set<Integer> markIdx = new HashSet<>(); // position of '(' or ')' that is determined to remove
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) markIdx.add(i);
                else stack.pop();
            }
        }
        
        while (!stack.isEmpty()) {
            markIdx.add(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!markIdx.contains(i)) sb.append(c);   
        }
        
        return sb.toString();
    }
}