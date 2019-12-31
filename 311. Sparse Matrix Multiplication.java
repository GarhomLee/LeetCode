https://leetcode.com/problems/sparse-matrix-multiplication/

思路：Math，根据定义计算
时间复杂度：O(m*n*p)
空间复杂度：O(m*p)

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = m == 0 ? 0 : A[0].length, p = B.length == 0 ? 0 : B[0].length;
        int[][] res = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) continue;
                for (int k = 0; k < p; k++) {
                    if (B[j][k] == 0) continue;
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }
}