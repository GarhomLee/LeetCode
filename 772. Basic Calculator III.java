https://leetcode.com/problems/basic-calculator-iii/

对比：224. Basic Calculator和227. Basic Calculator II的结合版，同时出现加减乘除号和括号

思路：Stack + Math

时间复杂度：O(s), s=s.length()
空间复杂度：O(s), s=s.length()

class Solution {
    String ops = "+-*/";
    
    public int calculate(String s) {
        int res = 0, curr = 0, pre = 0;
        char preOp = '+';
        Stack<Integer> stack = new Stack();
        
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == ' ') continue;
            
            if (i < s.length() && Character.isDigit(s.charAt(i))) {
                curr = curr * 10 + (s.charAt(i) - '0');
            } else if (i < s.length() && s.charAt(i) == '(') {  // cache all info into Stack
                stack.push(res);
                stack.push(pre);
                stack.push(ops.indexOf(preOp));
                preOp = '+';
                pre = 0;
                res = 0;
                curr = 0;
            } else {  // either ')', or operators "+-*/", or the end of String s
                /* calculate the res and update pre and curr no matter which case it is */
                switch (preOp) {
                    case '+':
                        res += curr;
                        pre = curr;
                        break;
                    case '-':
                        res -= curr;
                        pre = -curr;
                        break;
                    case '*':
                        res = res - pre + pre * curr;
                        pre = pre * curr;
                        break;
                    case '/':
                        res = res - pre + pre / curr;
                        pre = pre / curr;
                        break;
                }
                curr = 0;  // reset curr
                
                // if it is not the end, it might be ')' or operators "+-*/"
                if (i < s.length()) {
                    if (s.charAt(i) == ')') {  // reset res, pre and curr based on the cached info in the Stack,
                                                // but don't have to calculate the new res, since it will be calculated
                                                // when it hits the end or the operators
                        preOp = ops.charAt(stack.pop());
                        curr = res;
                        pre = stack.pop();
                        res = stack.pop();
                    } else {  // update preOp
                        preOp = s.charAt(i);
                    }
                }
            }
        }
        
        return res;
    }
}