https://leetcode.com/problems/surrounded-regions/

// 思路：DFS，two-pass
//      从边缘开始向内DFS搜索。
//      终止条件：1）越界
//              2）不是'O'，即有可能是'X'或者'*'。
//      将和边界有连接的'O'标记成'*'，然后递归搜索它的相邻元素。
//      将所有元素标记完成后，再一次遍历board数组。
//      如果有'O'，说明没有被标记为'*'，说明不和边界相连接，所以需要变成'X'。
//      如果有'*'，说明原来是和边界相连的'O'，所以要维持为'O'。
// 犯错点：1.思路错误，不能从中间向外搜索

class Solution {
    int[] dir = new int[]{0, 1, 0, -1, 0};
    int[][] mark;
    
    public void solve(char[][] board) {
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        mark = new int[rowLen][colLen];
         /* search from the border */
        for (int col = 0; col < colLen; col++) {
            dfs(board, 0, col);
            dfs(board, rowLen - 1, col);
        }
        for (int row = 0; row < rowLen; row++) {
            dfs(board, row, 0);
            dfs(board, row, colLen - 1);
        }
        /* search and process the marks */
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (board[row][col] == 'O') board[row][col] = 'X';  // the 'O' not turned into '*' is not connected to the border, so it should be flipped into 'X'
                else if (board[row][col] == '*') board[row][col] = 'O';  // the 'O' that turned into '*' is connected to the border, so it should remain as 'O'
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col) {
        /* termination conditions: out of boundary, or not connected 'O' which means it might be 'X' or '*'. */
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') return;
        
        board[row][col] = '*';  // mark the 'O' connected to the border as '*'
        /* search its adjacent neighbors */
        for (int i = 0; i < 4; i++) {
            dfs(board, row + dir[i], col + dir[i + 1]);
        }
    }
}