https://leetcode.com/problems/count-submatrices-with-all-ones/

solution1: Prefix Sum
time complexity: O(m^2*n^2)
space complexity: O(m*n)

class Solution {
    private int getCount(int[][] sum, int row, int col) {
        int count = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int partial = sum[row][col] - sum[row - i][col] - sum[row][col - j] + sum[row - i][col - j];
                if (partial != i * j) break;
                
                count++;
            }
        }
        
        return count;
    }
    
    public int numSubmat(int[][] mat) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;
        int[][] sum = new int[rowLen + 1][colLen + 1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sum[row][col] = mat[row - 1][col - 1] + sum[row][col - 1] + sum[row - 1][col] - sum[row - 1][col - 1];
            }
        }
        
        int ret = 0;
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                ret += getCount(sum, row, col);
            }
        }
        
        return ret;
    }
}


solution 2: DP. Referring to: https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720227/Pre-computation-VIDEO-solution-O(m*n*n)
time complexity: O(m*n^2)
space complexity: O(m*n)