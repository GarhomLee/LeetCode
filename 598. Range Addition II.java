https://leetcode.com/problems/range-addition-ii/

// 思路：数学问题
//         找最大重合面积，因此求最小的长和最小的宽。
// 时间复杂度：O(k), k=ops.length
// 空间复杂度：O(1)
// 犯错点：1.边界条件错误：ops数组可能为空，这时最大面积即整个矩阵的面积。

class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        //{Mistake 1}
        /* base case */
        if (ops.length == 0) return m * n;  // {Correction 1}
        
        int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE;
        for (int[] pair: ops) {
            minRow = Math.min(minRow, pair[0]);
            minCol = Math.min(minCol, pair[1]);
        }
        return minRow * minCol;
    }
}

// 另一种写法：直接利用变量m和n

public class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}