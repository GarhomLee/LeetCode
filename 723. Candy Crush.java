https://leetcode.com/problems/candy-crush/

思路：Simulation + Recursion。参考：https://leetcode.com/problems/candy-crush/solution/

时间复杂度：O((rowLen*colLen)^2)
空间复杂度：O(1)

class Solution {
    public int[][] candyCrush(int[][] board) {
        boolean hasCrush = false;
        int rowLen = board.length, colLen = board[0].length;
        /* search if there is crush happening horizontally */
        for (int row = 0; row < rowLen; row++) {
            for (int col = 2; col < colLen; col++) {
                int first = Math.abs(board[row][col]), second = Math.abs(board[row][col - 1]), third = Math.abs(board[row][col - 2]);
                if (first == second && second == third && first != 0) {
                    hasCrush = true;
                    // mark as negative number
                    board[row][col] = -first;
                    board[row][col - 1] = -first;
                    board[row][col - 2] = -first;
                }
            }
        }
        /* search if there is crush happening vertically */
        for (int col = 0; col < colLen; col++) {
            for (int row = 2; row < rowLen; row++) {
                int first = Math.abs(board[row][col]), second = Math.abs(board[row - 1][col]), third = Math.abs(board[row - 2][col]);
                if (first == second && second == third && first != 0) {
                    hasCrush = true;
                    // mark as negative number
                    board[row][col] = -first;
                    board[row - 1][col] = -first;
                    board[row - 2][col] = -first;
                }
            }
        }

        /* base case */
        if (!hasCrush) return board;
        
        /* update the board */
        for (int col = 0; col < colLen; col++) {
            for (int up = rowLen - 1, down = rowLen - 1; up >= 0; up--) {
                if (board[up][col] <= 0) {
                    board[up][col] = 0;
                } else {
                    int temp = board[up][col];
                    board[up][col] = 0;
                    board[down--][col] = temp;
                }
            }
        }
        
        /* recursion */
        return candyCrush(board);
    }
}