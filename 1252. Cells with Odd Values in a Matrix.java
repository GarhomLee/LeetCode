https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/

// 思路：Simulation
// 时间复杂度：O(l*(m+n) + m*n), l=indices.length
// 空间复杂度：O(1)

class Solution {
    public int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        for (int[] index : indices) {
            int row = index[0], col = index[1];
            for (int i = 0; i < n; i++) {
                matrix[i][col]++;
            }
            for (int i = 0; i < m; i++) {
                matrix[row][i]++;
            }
        }
        
        int res = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (matrix[row][col] % 2 == 1) {
                    res++;
                }
            }
        }
        
        return res;
    }
}