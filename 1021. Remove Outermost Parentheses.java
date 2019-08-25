https://leetcode.com/problems/remove-outermost-parentheses/

// 思路：常规遍历
//      step1: 维护变量leftCount，记录当前没有被右括号抵消的左括号个数。
//      step2: 维护左指针left和右指针right，表示当前左右括号平衡的子串范围。
//             遍历数组，当前字符s[right]为'('时leftCount++，为')'时leftCount--。
//             当leftCount==0时，找到需要去除一层括号的子串，将去掉最外层括号后的结果s[left+1,right)加入StringBuilder中。
//      step3: 返回StringBuilder的String形式。
// 时间复杂度：O(N)
// 空间复杂度：O(N)
// 犯错点：1.思路错误：只需要去除一层的最外层parenthesis即可，不需要DFS。

class Solution {
    public String removeOuterParentheses(String s) {
        if (s.length() == 0) {
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        int leftCount = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            leftCount += c == '(' ? 1 : -1;
            if (leftCount == 0) {
                //sb.append(removeOuterParentheses(s.substring(left + 1, right)));  // {Mistake 1}
                sb.append(s.substring(left + 1, right));  // {Correction 1}
                left = right + 1;
            }
        }
        
        return sb.toString();
    }
}