https://leetcode.com/problems/game-of-life/

// 解法一、copy一个二维数组，然后in-place改变原二维数组。
// 1）nested loop扫描每一个cell
// 2）对于每个cell，用一个helper method来实现update
// 3）在helper method中，扫描当前cell的8个neighbor，维护一个count来数live neighbor cells。当count == 3 || (count == 2 && current cell is live)时，在原二维数组中将当前cell标记为1
// 4）时间复杂度：O(m*n)；空间复杂度：O(m*n)

class Solution {
    //int[] dir = new int[]{0, -1, 0, 1, 0};
    public void gameOfLife(int[][] board) {
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        int[][] copy = new int[rowLen][colLen];
        for (int row = 0; row < rowLen; row++) {
            System.arraycopy(board[row], 0, copy[row], 0, colLen);
        }
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                update(board, copy, row, col);
            }
        }
    }
    private void update(int[][] board, int[][] copy, int row, int col) {
        int count = 0;
        for (int currRow = row - 1; currRow <= row + 1; currRow++) {
            for (int currCol = col - 1; currCol <= col + 1; currCol++) {
                if (currRow < 0 || currRow >= board.length || currCol < 0 || currCol >= board[0].length || (currRow == row && currCol == col)) continue;
                if (copy[currRow][currCol] == 1) count++;
            }
        }
        if (count == 3 || (count == 2 && copy[row][col] == 1)) board[row][col] = 1;
        else board[row][col] = 0;
    }
}

// 解法二、利用bit的最后两位记录状态。最后一位是current state，倒数第二位是next state。00: dead <- dead; 01: dead <- live; 10: live <- dead; 11: live <- live
// 1）2）同上
// 3）用board[row][col] & 1，得到最后一位bit表示的current state，进而count live cells。update过后，所有元素从0，1变为2，3
// 4）再次遍历所有元素，右移一位，得到新的状态
// 5）时间复杂度：O(m*n)；空间复杂度：O(1)