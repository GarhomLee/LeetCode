https://leetcode.com/problems/island-perimeter/

// 思路：找规律
//        可以发现，由于题目的限制，岛的周长一定等于每个小方格的周长（边长为1，周长为4）减去和其他小方格接触的边的数量。

class Solution {
    int[] dir = new int[]{0, 1, 0, -1, 0};
    
    public int islandPerimeter(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int landCount = 0, contactCount = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                /* skip 0s */
                if (grid[row][col] == 0) continue;
                /* count the number of lands as well as the contacts */
                landCount++;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    if (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && grid[nextRow][nextCol] == 1) contactCount++;
                }
            }
        }
        return landCount * 4 - contactCount;
    }
}

// 另一种写法：只需要数右边和下边的相邻小方格，对于每个这样的小方格*2，再用landCount * 4减去。