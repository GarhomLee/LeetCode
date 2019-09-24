https://leetcode.com/problems/design-tic-tac-toe/

// 思路：用数组缓存每个player的每行、每列、对角线、反对角线的棋子个数，如果有某个player的某一行、某一列、对角线、
//         或反对角线的棋子个数到达了n，那么这个player获胜。
// 时间复杂度：move(): O(1)
// 空间复杂度：O(n)

class TicTacToe {
    int[][] h;
    int[][] v;
    int[] d;
    int[] rd;
    int dimension;
    
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        h = new int[2][n];
        v = new int[2][n];
        d = new int[2];
        rd = new int[2];
        dimension = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        h[player - 1][col]++;
        v[player - 1][row]++;
        if (row == col) {
            d[player - 1]++;
        }
        if (row + col == dimension - 1) {
            rd[player - 1]++;
        }
        
        if (h[player - 1][col] == dimension || v[player - 1][row] == dimension
           || d[player - 1] == dimension || rd[player - 1] == dimension) {
            return player;
        }
        
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */