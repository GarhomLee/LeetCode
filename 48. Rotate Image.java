https://leetcode.com/problems/rotate-image/

// 可以利用数学性质取巧，将数组顺时针翻转90度。逆时针的翻转方式有所不同。
// 1）先将数组上下翻转
// 2）再将数组沿对角线翻转，即翻转上下三角矩阵

class Solution {
    public void rotate(int[][] matrix) {
        int dimension = matrix.length;
        if (dimension == 0) return;
        
        int startRow = 0, endRow = dimension - 1;
        
        /* turn the matrix upside down */
        while (startRow < endRow) {
            int[] tempRow = matrix[startRow];
            matrix[startRow++] = matrix[endRow];
            matrix[endRow--] = tempRow;
        }
        
        /* swap elements of lower triangle with those of upper triangle */
        for (int row = 1; row < dimension; row++) {
            for (int col = 0; col < row; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        
    }
}