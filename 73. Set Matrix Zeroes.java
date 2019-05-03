https://leetcode.com/problems/set-matrix-zeroes/

// 遍历两次。第一次记录哪些行列需要变为0，第二次将这些行列变为0。
// 1）如果遇到0，那么将这一行和这一列的第一个元素都设为0作为标记。因此会产生一个问题，那就是无法分辨第一行和第一列中出现的0到底是因为原本在第一行/列就有0，需要变为0，还是来自于标记的0。因此，维护两个boolean值来记录是否在第一行和第一列原本就有0
// 2）第二次遍历时，从row = 1和col = 1开始遍历，并将对应行列变为0
// 3）第一行和第一列单独遍历，根据boolean值来决定是否变为0
// 4）时间复杂度：O(m*n)；空间复杂度：O(1)

class Solution {
    public void setZeroes(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        boolean isFirstRowZero = false, isFirstColZero = false;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] == 0) {
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                    if (row == 0) isFirstRowZero = true;
                    if (col == 0) isFirstColZero = true;
                }
            }
        }
        
        for (int row = 1; row < rowLen; row++) {
            for (int col = 1; col < colLen; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) matrix[row][col] = 0;
            }
        }
        
        if (isFirstRowZero) {
            for (int col = 0; col < colLen; col++) matrix[0][col] = 0;
        }
        if (isFirstColZero) {
            for (int row = 0; row < rowLen; row++) matrix[row][0] = 0;
        }
    }
}