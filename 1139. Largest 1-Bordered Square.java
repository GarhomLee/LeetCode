https://leetcode.com/problems/largest-1-bordered-square/

// 思路：prefix sum
//         先求从左上角到每个右下角[row][col]位置所形成的矩形的和。
//         然后，对于每个右下角[row][col]所能形成的正方形，从大到小搜索。如果外层正方形和内层正方形的差值正好为外层正方形边缘都为1
//         时的和，那么更新maxArea。

class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
		
		/* record the sum from [0,0] to [row,col] */
        int sums[][] = new int[rowLen + 1][colLen + 1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sums[row][col] = sums[row - 1][col] + sums[row][col - 1] + grid[row - 1][col - 1] - sums[row - 1][col - 1];
            }
        }
        
        int maxArea = 0;
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                for (int len = Math.min(row, col); len > 0; len--) {
                    int area = sums[row][col] - sums[row - len][col] - sums[row][col - len] + sums[row - len][col - len];
                    int innerArea = len == 1 ? 0 : sums[row - 1][col - 1] - sums[row - len + 1][col - 1] - sums[row - 1][col - len + 1] + sums[row - len + 1][col - len + 1];
                    if ((len == 1 && area == 1) || (len > 1 && area - innerArea == len * 2 + (len - 2) * 2)) {
                        maxArea = Math.max(maxArea, len * len);
                    }
                }
            }
        }
        
        
        return maxArea;
    }
}