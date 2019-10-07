https://leetcode.com/problems/toeplitz-matrix/

// 思路：Brute Force，比较每个数matrix[row][col]是否和对角线的前一个数matrix[row - 1][col - 1]相同。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(1)

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (row - 1 >= 0 && col - 1 >= 0 && matrix[row][col] != matrix[row - 1][col - 1]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}