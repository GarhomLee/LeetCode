https://leetcode.com/problems/basic-calculator-ii/submissions/

// 类似224. Basic Calculator，本题没有括号，增加了'*'和'/'。

// 思路：利用Stack，存储所有被'+'和'-'分割的元素。这就意味着，如果是'*'或'/'，需要将stack.top拿出来，进行乘除运算后，再放回去，即更新了stack.top
//      遍历扫描结束后，将Stack里的所有元素相加，即为结果。
//      要注意的是，每遇到新的operator或String s扫描结束，当前的数值curr要和【之前的那个operator进行运算】，存在preOp变量里。运算完再更新preOp为当前operator。
// 犯错点：1.更新preOp前，要判断index i是不是仍然在String s范围内。

class Solution {
    public int calculate(String s) {
        int curr = 0;
        char preOp = '+';
        Stack<Integer> stack = new Stack();
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == ' ') continue;  // skip space
            
            if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {  // update numeric value
                curr = curr * 10 + (s.charAt(i) - '0');
            } else {  // encouter a operator, or reach the end of String s
                switch (preOp) {
                    case '+': stack.push(curr); break;
                    case '-': stack.push(-curr); break;
                    case '*': stack.push(stack.pop() * curr); break;
                    case '/': stack.push(stack.pop() / curr); break;
                }
                //preOp = s.charAt(i);  // {Mistake 1: index i might be out of boundary}
                if (i < s.length()) preOp = s.charAt(i);  //  {Correction 1: evaluate index i first}
                curr = 0;  // reset curr
            }
        }
        
        int res = 0;
        for (int num: stack) {  // using iterator instead of popping elements can accelerate this process
            res += num;
        }
        return res;
    }
}

// 优化：利用数组+top指针模拟Stack

class Solution {
    public int calculate(String s) {
        int curr = 0;
        char preOp = '+';
        int[] nums = new int[s.length()];  // use array to simulate a Stack
        int top = -1;
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == ' ') continue;
            
            if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                curr = curr * 10 + (s.charAt(i) - '0');
            } else {
                switch (preOp) {
                    case '+': nums[++top] = curr; break;
                    case '-': nums[++top] = -curr; break;
                    case '*': nums[top] *= curr; break;
                    case '/': nums[top] /= curr; break;
                }
                if (i < s.length()) preOp = s.charAt(i);
                curr = 0;
            }
        }
        
        int res = 0;
        for (int i = 0; i <= top; i++) {
            res += nums[i];
        }
        return res;
    }
}


// 解法二：Math，利用变量pre处理乘除的情况

class Solution {
    public int calculate(String s) {
        int res = 0, pre = 0, curr = 0;
        char preOp = '+';
        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length() || !Character.isDigit(s.charAt(i))) {
                if (i < s.length() && s.charAt(i) == ' ') continue;
                
                if (preOp == '+') {
                    res += curr;
                    pre = curr;
                } else if (preOp == '-') {
                    res -= curr;
                    pre = -curr;
                } else if (preOp == '*') {
                    res = res - pre + pre * curr;
                    pre = pre * curr;
                } else if (preOp == '/') {
                    res = res - pre + pre / curr;
                    pre = pre / curr;
                }
                
                curr = 0;
                
                if (i < s.length()) {
                    preOp = s.charAt(i);
                }
            } else {
                curr = curr * 10 + (s.charAt(i) - '0');
            }
        }
        
        return res;
    }
}