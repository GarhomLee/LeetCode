https://leetcode.com/problems/backspace-string-compare/

// 思路：Stack
// 时间复杂度：O(s + t), s=s.length(), t=t.length()
// 空间复杂度：O(s + t), s=s.length(), t=t.length()

class Solution {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>(), tStack = new Stack<>();
        return build(S, sStack).equals(build(T, tStack));
    }
    
    private String build(String s, Stack<Character> stack) {
        for (char c: s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        return sb.reverse().toString();
    }
}


follow-up:如果空间复杂度只能为O(1)？