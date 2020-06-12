https://leetcode.com/problems/count-square-submatrices-with-all-ones/

solution1: Dynamic Programming
time complexity: O(m*n)
space complexity: O(m*n)

class Solution {
    public int countSquares(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        int[][] dp = new int[rowLen + 1][colLen + 1];
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (matrix[row][col] != 0) {
                    dp[row + 1][col + 1] = 1 + Math.min(dp[row][col], Math.min(dp[row + 1][col], dp[row][col + 1]));
                }
                res += dp[row + 1][col + 1];
            }
        }
        
        return res;
    }
}


solution2: Prefix Sum
time complexity: O(m*n*min(m,n))
space complexity: O(m*n)

class Solution {
    public int countSquares(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        int[][] sum = new int[rowLen + 1][colLen + 1];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                sum[row + 1][col + 1] = matrix[row][col] 
                                        + sum[row][col + 1] + sum[row + 1][col] 
                                        - sum[row][col];
            }
        }
        
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                for (int k = 1; row + 1 - k >= 0 && col + 1 - k >= 0; k++) {
                    int smallRow = row + 1 - k, smallCol = col + 1 - k;
                    int squareSum = sum[row + 1][col + 1] 
                                    - sum[smallRow][col + 1] - sum[row + 1][smallCol] 
                                    + sum[smallRow][smallCol];
                    if (squareSum != k * k) break;
                    
                    res++;
                }
            }
        }
        
        return res;
    }
}