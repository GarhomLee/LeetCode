https://leetcode.com/problems/evaluate-reverse-polish-notation/

// 思路：简单的Stack应用。
//      如果遇到操作符，将Stack的顶部两个元素利用该操作符进行计算，将结果push进Stack里。其他情况下，将String转成Integer，push进Stack里。
//      注意'-'和'/'的操作顺序，第一个pop出来的元素num2是减数（或除数），第二个pop出来的元素num1是被减数（被除数），即num1-num2（或num1/num2)。

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (String s: tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                char c = s.charAt(0);
                int num2 = stack.pop(), num1 = stack.pop();
                switch (c) {
                    case '+': stack.push(num1 + num2); break;
                    case '-': stack.push(num1 - num2); break;
                    case '*': stack.push(num1 * num2); break;
                    case '/': stack.push(num1 / num2); break;
                }
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }
}

// 优化：利用数组+top指针模拟Stack。

class Solution {
    public int evalRPN(String[] tokens) {
        int[] res = new int[tokens.length];
        int top = -1;
        
        for (String s: tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                char c = s.charAt(0);
                int num2 = res[top--], num1 = res[top--];
                switch (c) {
                    case '+': res[++top] = num1 + num2; break;
                    case '-': res[++top] = num1 - num2; break;
                    case '*': res[++top] = num1 * num2; break;
                    case '/': res[++top] = num1 / num2; break;
                }
            } else {
                res[++top] = Integer.parseInt(s);
            }
        }
        return res[0];
    }
}