https://leetcode.com/problems/valid-parentheses/

// 总体思路：parentheses的配对符合LIFO的性质，所以可以用Stack来做。
// 小技巧：优先处理右半括号，因为左半括号只有push()的操作。
// 注意：最后需要注意Stack里还有没有未处理的左半括号，如果有，需要返回false。

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') return false;
                else stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') return false;
                else stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') return false;
                else stack.pop();
            } else stack.push(c);
        }
        return stack.isEmpty();
    }
}