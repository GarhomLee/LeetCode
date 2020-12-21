https://leetcode.com/problems/longest-line-of-consecutive-one-in-matrix/

idea: DP
time complexity: O(4*rowLen*colLen)
space complexity: O(4*rowLen*colLen)

class Solution {
    public int longestLine(int[][] M) {
        int rowLen = M.length, colLen = rowLen == 0 ? 0 : M[0].length;
        int[][][] lens = new int[4][rowLen][colLen];    // lens[i] = { horizontal, vertical, diagonal 
                                                        // or anti-diagonal}
        int maxLen = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (M[row][col]  == 0) continue;
                
                lens[0][row][col] = 1 + (col >= 1 ? lens[0][row][col - 1] : 0);   // horizontal
                lens[1][row][col] = 1 + (row >= 1 ? lens[1][row - 1][col] : 0);   // vertical
                lens[2][row][col] = 1 + (row >= 1 && col >= 1 ? lens[2][row - 1][col - 1] : 0);   // diagonal
                lens[3][row][col] = 1 + (row >= 1 && col + 1 < colLen ? lens[3][row - 1][col + 1] : 0);    // anti-diagonal
                for (int i = 0; i < 4; i++) {
                    maxLen = Math.max(maxLen, lens[i][row][col]);
                }
                
            }
        }
        
        return maxLen;
    }
}