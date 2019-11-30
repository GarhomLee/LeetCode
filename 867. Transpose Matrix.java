https://leetcode.com/problems/transpose-matrix/

// 思路：Math，矩阵transpose定义
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)

class Solution {
    public int[][] transpose(int[][] A) {
        int rowLen = A.length, colLen = rowLen == 0 ? 0 : A[0].length;
        int[][] res = new int[colLen][rowLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                res[col][row] = A[row][col];
            }
        }
        
        return res;
    }
}