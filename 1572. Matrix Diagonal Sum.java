https://leetcode.com/problems/matrix-diagonal-sum/

idea: Brute Force
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for (int row = 0; row < n; row++) {
            int col1 = row, col2 = n - 1 - row;
            sum += mat[row][col1] + mat[row][col2];
            if (col1 == col2) {
                sum -= mat[row][col1];
            }
        }
        
        return sum;
    }
}