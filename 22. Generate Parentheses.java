https://leetcode.com/problems/generate-parentheses/

// 20. Valid Parentheses的类似题。

// 总体思路：套用Backtracking模板，找到goal，choices，constraints。
// 注意：可以不需要用StringBuilder来得到String，因为String的immutability的性质，所以可以直接用concatenation，不需要像List一样reset。

class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        find(0, 0, n, "");
        return res;
    }
    private void find(int leftCount, int rightCount, int n, String s) {
        if (leftCount == n && rightCount == n) {  // GOAL: enough left and right parenthesis
            res.add(s);
            return;
        }

        // CHOICES: either left or right parenthesis
        // CONSTRAINTS: (1) the number of both left and right parenthesis should be less than n
        //              (2) the number of right parenthesis should be less than that of left parenthesis, which has included (1)

        if (leftCount < n) {find(leftCount + 1, rightCount, n, s + "(");  
        
        if (rightCount < leftCount) find(leftCount, rightCount + 1, n, s + ")");
    }
}