https://leetcode.com/problems/remove-invalid-parentheses/

// 思路：Recursion (DFS) + BackTracking。视频讲解：https://www.youtube.com/watch?v=2k_rS_u6EBk
//         首先，用两个变量leftCount和rightCount记录至少要删除多少左括号和右括号才能使得整个括号组合法。
//         然后，利用递归Backtracking进行删除和记录合法结果。
//         goal：多余的左括号和右括号都删除了，leftCount == 0 && rightCount == 0，且剩下的括号组是合法的。
//                 判断括号组是否合法要再利用helper method，遍历记录左括号个数，如果过程中右括号比左括号多，
//                 那么不合法；遍历完成后，如果左括号比右括号多，也不合法。
//         choices：从start开始到最后一个字符
//         constraints：1.如果rightCount > 0，那么【先把右括号全部删除】。只有当rightCount==0，才开始删除左括号。
//                     2.只能删除括号，其他字符都跳过.
//                     3.遇到连续左括号或右括号，只删除第一个，即跳过连续的左括号或右括号。
// 犯错点：1.Java内置函数使用错误：如果StringBuilder长度为0（里面没有字符），那么toString()不会返回空字符串""，
//             而是什么都不返回。因此，需要额外判断StringBuilder为空时要加入的空字符串""。
//         2.Java内置函数使用错误：StringBuilder没有isEmpty()，只能用length()判断是否长度为0。

class Solution {
    List<String> res = new ArrayList();
    
    public List<String> removeInvalidParentheses(String s) {
        int leftCount = 0, rightCount = 0;
        
        /* determine how many invalid left and right parentheses to be removed */
        for (char c: s.toCharArray()) {
            if (c == ')') {
                if (leftCount == 0) rightCount++;  // more ')' than '('
                else if (leftCount > 0) leftCount--;
            }
            else if (c == '(') {
                leftCount++;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        dfs(sb, 0, leftCount, rightCount);

        return res;
    }
    
    private void dfs(StringBuilder sb, int start, int leftCount, int rightCount) {
        /* base case */
        if (leftCount == 0 && rightCount == 0) {  // GOAL
            if (isValid(sb)) {
                //res.add(sb.toString());  // {Mistake 1}
                //res.add(sb.isEmpty() ? "" : sb.toString());  // {Mistake 2}
                res.add(sb.length() == 0 ? "" : sb.toString());  // {Correction 1} {Correction 2}
            }
            return;
        }
        if (start == sb.length()) return;
        
        // CHOICES
        /* try to remove all invalid right parentheses ')' first, then remove left parentheses '(' */
        if (rightCount > 0) {
            for (int i = start; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c != ')' || (i != start && c == sb.charAt(i - 1))) continue;  // CONSTRAINTS: skip non ')' or contiguous ')'
                sb.deleteCharAt(i);
                dfs(sb, i, leftCount, rightCount - 1);
                sb.insert(i, c);
            }
        } else if (leftCount > 0) {
            for (int i = start; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c != '(' || (i != start && c == sb.charAt(i - 1))) continue;  // CONSTRAINTS: skip non '(' or contiguous '('
                sb.deleteCharAt(i);
                dfs(sb, i, leftCount - 1, rightCount);
                sb.insert(i, c);
            }
        }
        
    }
    
    /** evaluate if a String contains valid parentheses */
    private boolean isValid(StringBuilder sb) {
        int leftCount = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ')') {
                if (leftCount == 0) return false;
                else if (leftCount > 0) leftCount--;
            } else if (sb.charAt(i) == '(') {
                leftCount++;
            }
        }
        return leftCount == 0;
    }
}