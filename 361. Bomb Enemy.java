https://leetcode.com/problems/bomb-enemy/

思路：Dynamic Programming
        状态函数定义：
        初始值：
        状态转移方程：
时间复杂度：O(rowLen * colLen)
空间复杂度：O(colLen)

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int[] currCol = new int[colLen];
        int max = 0;
        for (int row = 0; row < rowLen; row++) {
            int currRow = 0;
            for (int col = 0; col < colLen; col++) {
                if (row == 0 || grid[row - 1][col] == 'W') {
                    currCol[col] = 0;
                    for (int i = row; i < rowLen && grid[i][col] != 'W'; i++) {
                        currCol[col] += grid[i][col] == 'E' ? 1 : 0;
                    }
                }
                
                if (col == 0 || grid[row][col - 1] == 'W') {
                    currRow = 0;
                    for (int i = col; i < colLen && grid[row][i] != 'W'; i++) {
                        currRow += grid[row][i] == 'E' ? 1 : 0;
                    }
                }
                
                if (grid[row][col] == '0') {
                    max = Math.max(max, currRow + currCol[col]);
                }
                
            }
        }
        
        return max;
    }
}