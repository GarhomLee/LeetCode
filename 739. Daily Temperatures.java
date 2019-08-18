https://leetcode.com/problems/daily-temperatures/

// 思路：Stack，维护decreasing subsequence
//         用Stack存放index，当遇到当前T[i]大于T[stack[top]]，说明有一部分结果，为i-stack[top]。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        int[] stack = new int[T.length + 1];
        int top = -1;
        for (int i = 0; i < T.length; i++) {
            int curr = T[i];
            while (top >= 0 && curr > T[stack[top]]) {
                res[stack[top]] = i - stack[top];
                top--;
            }
            stack[++top] = i;
        }
        
        /*while (top >= 0) {
            res[stack[top--]] = 0;
        }*/  // unnecessary
        
        return res; 
    }
}