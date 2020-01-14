https://leetcode.com/problems/matrix-block-sum/

思路：Prefix Sum
        首先，求出prefix sum。
        然后，对于每个res[row][col]，得到实际的行范围[rl:ru]和列范围[cl:cu]，根据prefix sum求出
        范围内的partial sum。
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)

class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;

        // calculate prefix sum
        int[][] sum = new int[rowLen + 1][colLen + 1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sum[row][col] = mat[row - 1][col - 1] + sum[row - 1][col] + sum[row][col - 1] - sum[row - 1][col - 1];
            }
        }
        
        // find lower and upper bound of row (rl, ru) and column (cl, cu), use prefix sum
        // to calculate partial sum
        int[][] res = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int rl = Math.max(0, row - K), ru = Math.min(rowLen - 1, row + K);
                int cl = Math.max(0, col - K), cu = Math.min(colLen - 1, col + K);
                res[row][col] = sum[ru + 1][cu + 1] - sum[rl][cu + 1] - sum[ru + 1][cl] + sum[rl][cl];
            }
        }
        
        return res;
    }
}