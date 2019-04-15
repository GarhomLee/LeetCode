// https://leetcode.com/problems/spiral-matrix-ii/

// 54题的follow-up。可以用同样的一个模板，在每个循环中进行4次判断。
// 1）维护4个（2对）变量，分别表示首行/列和尾行/列。
// 2）按右、下、左、上的顺序遍历matrix并加入list。由于问题的特殊性，实际上不需要进行if判断也可以。

class Solution {
    public int[][] generateMatrix(int n) {
        int element = 1;
        int startRow = 0, endRow = n - 1, startCol = 0, endCol = n - 1;
        int[][] matrix = new int[n][n];
        while (startRow <= endRow && startCol <= endCol) {
            if (startRow <= endRow) {
                for (int col = startCol; col <= endCol; col++) matrix[startRow][col] = element++;
                startRow++;
            }
            if (startCol <= endCol) {
                for (int row = startRow; row <= endRow; row++) matrix[row][endCol] = element++;
                endCol--;
            }
            if (startRow <= endRow) {
                for (int col = endCol; col >= startCol; col--) matrix[endRow][col] = element++;
                endRow--;
            }
            if (startCol <= endCol) {
                for (int row = endRow; row >= startRow; row--) matrix[row][startCol] = element++;
                startCol++;
            }
        }
        return matrix;
    }
}