https://leetcode.com/problems/basic-calculator/

// 类似227. Basic Calculator II，本题只有'+'和'-'，同时增加了括号，所以只需考虑不同"()"范围的优先级情况。

// 思路：扫描字符串，可能遇到以下几种情况（跳过空格）：
//      1）数字，更新curr
//      2）'+'或'-'，表示当前的数字已经结束，可以进行运算了，需要利用前一个operator，然后更新到res。
//         更新preOp。
//      3）'('，表示要开辟新的scope，那么把已经求得的res和最近的operator存进Stack里，初始化res，curr和preOp。
//      4）')'，表示当前scope已经结束，那么先用curr和preOp更新完res，表示当前scope的结果，然后从Stack里取出
//         上一层的res和preOp，将res更新为上一层的最新res。如果String s扫描完，那么从Stack取res和preOp前需要判断
//         Stack是否为空。
// 犯错点：1.因为把String s以')'结尾和不以')'结尾的情况放在一起讨论，所以当扫描完String s时，不以')'结尾的情况下，
//         Stack为空，没有上一层的res和preOp，需要特殊处理。

class Solution {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack();
        Stack<Character> opStack = new Stack();
        
        int curr = 0;  // current processing number, which means the value separated by non-numeric char
        int res = 0;  // the result in the current scope
        char preOp = '+';  // the operator previous to curr
        for (int i = 0; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == ' ') continue;  // skip space
            
            if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {  // numeric char
                curr = curr * 10 + (s.charAt(i) - '0');  // update curr
            } else if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {  // encounter an new operator, deal with curr along with the operator previous to curr
                curr = preOp == '+' ? curr : -curr;
                res += curr;  // update the result in the current scope
                preOp = s.charAt(i);  // update preOp
                curr = 0;  // reset curr
            } else if (i < s.length() && s.charAt(i) == '(') {  // go into a new smaller scope
                numStack.push(res);  // store the result of current scope into Stack
                opStack.push(preOp);  // store the nearest operator into Stack
                curr = 0;  // reset
                res = 0;
                preOp = '+';
            } else if (i == s.length() || s.charAt(i) == ')') {  // when current scope ends, or reach the end of String s
                curr = preOp == '+' ? curr : -curr;
                res += curr;  // get the result of current scope
                //int preRes = numStack.pop();  // {Mistake 1: if the expression is not ended with ')', Stack would be empty}
                //preOp = opStack.pop();
                int preRes = numStack.isEmpty() ? 0 : numStack.pop();  // {Correction 1: check corner cases where the expression is not ended with ')'}
                preOp = opStack.isEmpty() ? '+' : opStack.pop();
                res = preOp == '+' ? res : -res;  // treat res as curr in the previous larger scope
                res += preRes;  // update res as the current result of the previous larger scope
                curr = 0;
                preOp = '+';
            }
        }
        return res;
    }
}

优化：由于只有'+'和'-'，所以可以只用一个变量int sign来表示'+'和'-'。
     取巧点在于，当遇到'('时，只需要同时将sum和sign加入Stack，表示二者相关联。
     同时，最后不以')'结尾的情况单独出来考虑，这样增加了代码可读性。

class Solution {
    public int calculate(String s) {
        Deque<Integer> sumStack = new ArrayDeque<>(), signStack = new ArrayDeque<>();
        int sign = 1, sum = 0, curr = 0;    // sum: sum of the current level; curr: value of current number
        for (char c: s.toCharArray()) {
            if (c == ' ') continue;
            
            if (Character.isDigit(c)) {
                curr = curr * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                sum += sign * curr;
                curr = 0;
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') {
                sumStack.push(sum);
                sum = 0;
                
                signStack.push(sign);
                sign = 1;
            } else {
                sum += sign * curr;
                sum = sumStack.pop() + (signStack.pop() * sum);
                
                sign = 1;
                curr = 0;
            }
        }
        
        sum += sign * curr;
        
        return sum;
    }
}