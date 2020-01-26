https://leetcode.com/problems/valid-tic-tac-toe-state/

思路：记录当前'X'和'O'的行，列，对角，反对角的状态，最后判断当前状态是否合法。
        合法状态：'X'和'O'个数相同，或者'X'比'O'多一个（因为'X'先手）。
        1）如果'X'和'O'个数相同，那么'X'一定不能胜利，否则'X'胜利时游戏就该停止，此时应该有'X'比'O'多一个。
        2）如果'X'比'O'多一个，那么'O'一定不能胜利，否则'O'胜利时游戏就该停止，此时应该有'X'和'O'个数相同。
时间复杂度：O(3^2)
空间复杂度：O(3)

class Solution {
    public boolean validTicTacToe(String[] board) {
        int[] xrow = new int[3], xcol = new int[3];
        int[] orow = new int[3], ocol = new int[3];
        int xdiag = 0, xanti = 0, odiag = 0, oanti = 0;
        int xcount = 0, ocount = 0;
        boolean xend = false, oend = false;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char c = board[row].charAt(col);
                if (c == 'X') {
                    xcount++;
                    xrow[row]++;
                    xcol[col]++;
                    if (row == col) {
                        xdiag++;
                    }
                    if (row + col == 2) {
                        xanti++;
                    }
                    
                    if (xrow[row] == 3 || xcol[col] == 3 || xdiag == 3 || xanti == 3) {
                        xend = true;
                    }
                } else if (c == 'O') {
                    ocount++;
                    orow[row]++;
                    ocol[col]++;
                    if (row == col) {
                        odiag++;
                    }
                    if (row + col == 2) {
                        oanti++;
                    }
                    
                    if (orow[row] == 3 || ocol[col] == 3 || odiag == 3 || oanti == 3) {
                        oend = true;
                    }
                }
            }
        }
        
        return (xcount == ocount && !xend) || (xcount - ocount == 1 && !oend);
    }
}