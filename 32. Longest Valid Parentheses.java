https://leetcode.com/problems/longest-valid-parentheses/

// 大致有5种解法,详见https://leetcode.wang/leetCode-32-Longest-Valid-Parentheses.html
// 注意：题目的valid parentheses是指连续相接的substring，substring中必须左右括号数量相等。

// 解法一: Stack,利用Stack存储边界的index。
//         特别要注意的一点是，【Stack的底部永远放的是右括号')'的index】，往上的都是左括号'('的index。
//         Stack初始化时先放入第一个右括号')'的index，初始化为-1。向Stack中存取元素的规则是，如果当前字符是左括号'('，push入它的index。
//         如果当前字符是右括号')'，先pop出顶部存放的index（即和当前右括号匹配的左括号），这时候Stack剩余元素的顶部即为当前valid parentheses
//         的边界，用i - stack.peek()更新maxLen；当遇到特殊情况，即Stack为空，刚刚pop出的是栈底的右括号')'，说明当前')'没有与之匹配的左括号'('，
//         所以valid parentheses会重新开始计算，把当前的右括号')'的index加入栈底。

class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();  // Stack stores boundary position of each parentheses pair, whose very bottom must be the position of a ')'
        stack.push(-1);  // initialize with the position of virtual ")" as boundary
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ')') {
                stack.pop();  // pop out the top position, and peek the boundary position pairing with current ')'
                if (stack.isEmpty()) stack.push(i);  // if Stack becomes empty after pop(), it means no '(' pairs with current ')', so push current ')' position i as boundary
                else maxLen = Math.max(maxLen, i - stack.peek());
            } else stack.push(i);  // push '(' index into stack
            
        }
        return maxLen;
    }
}

// 解法二: Two-pass scanning，非常巧妙的一种解法。
//         根据题目的valid parentheses的性质，从左往右扫描，如果右括号')'数量比左括号'('多，就会形成断层，最长长度只能在左边或右边；
//         从右往左扫描，如果数量左括号'('比右括号')'多，也会形成断层，最长长度只能在左边或右边。最终的最长长度必然是同时满足从左往右扫描
//         和从右往左扫描都是最长。
// 注意：从左往右扫描时，只有当右括号')'数量等于左括号'('数量时，maxLen才能更新。当还有左括号'('没被右括号')'匹配时，多余的左括号'('也会
//     造成断层，不能更新maxLen。从右往左扫描与之类似。


class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        
        int leftCount = 0, rightCount = 0;  // the number of '(' and ')' respectively
        int maxLen = 0;  // the length of the longest valid parentheses
        
        for (int i = 0; i < s.length(); i++) {  // scan from left to right
            char c = s.charAt(i);
            if (c == '(') leftCount++;
            else rightCount++;
            
            if (rightCount > leftCount) {  // if there are more ')' than '(', reset leftCount and rightCount
                leftCount = 0;
                rightCount = 0;
            } else if (rightCount == leftCount) {  // only when rightCount == leftCount can contribute to maxLen
                maxLen = Math.max(maxLen, rightCount * 2);  // times 2 means pairs of parentheses
            }
        }
                
        leftCount = 0;  // reset
        rightCount = 0;  // rest
        
        for (int i = s.length() - 1; i >= 0; i--) {  // scan from right to left
            char c = s.charAt(i);
            if (c == '(') leftCount++;
            else rightCount++;
            
            if (leftCount > rightCount) {  // if there are more '(' than ')', reset leftCount and rightCount
                leftCount = 0;
                rightCount = 0;
            } else if (leftCount == rightCount) {  // only when rightCount == leftCount can contribute to maxLen
                maxLen = Math.max(maxLen, rightCount * 2);  // times 2 means pairs of parentheses
            }
        }
        return maxLen;
    }
}