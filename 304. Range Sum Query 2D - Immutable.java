https://leetcode.com/problems/range-sum-query-2d-immutable/

// 总体思路：303. Range Sum Query - Immutable的follow-up，将范围扩展到二维。注意求二维的range sum时对重叠部分的处理。

class NumMatrix {
    int[][] sums;
    public NumMatrix(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        sums = new int[rowLen + 1][colLen + 1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sums[row][col] = matrix[row - 1][col - 1] + sums[row][col - 1] + sums[row - 1][col] - sums[row - 1][col - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1] - sums[row2 + 1][col1] - sums[row1][col2 + 1] + sums[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */