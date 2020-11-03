https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/

idea: Greedy. Refer to: https://leetcode.com/problems/find-valid-matrix-given-row-and-column-sums/discuss/876845/JavaC%2B%2BPython-Easy-and-Concise-with-Prove
time complexity: O(rowLen*colLen)
space complexity: O(rowLen*colLen)

class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int rowLen = rowSum.length, colLen = colSum.length;
        int[][] res = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                res[row][col] = Math.min(rowSum[row], colSum[col]);
                rowSum[row] -= res[row][col];
                colSum[col] -= res[row][col];
            }
        }
        return res;
    }
}