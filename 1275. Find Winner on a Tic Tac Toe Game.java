https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/

// 思路：Simulation + Info Cache
// 时间复杂度：O(n), n=moves.length
// 空间复杂度：O(9) => O(1)

class Solution {
    public String tictactoe(int[][] moves) {
        int[][] rowCount = new int[2][3], colCount = new int[2][3];
        int[] diagCount = new int[2], antiDiagCount = new int[2];
        int total = 0; // total moves taken
        int player = 0; // current player: 0 is A, 1 is B
        
        for (int[] move : moves) {
            int row = move[0], col = move[1];
            rowCount[player][row]++;
            colCount[player][col]++;
            if (row == col) {
                diagCount[player]++;
            }
            if (row + col == 2) {
                antiDiagCount[player]++;
            }
            total++;
            
            if (rowCount[player][row] == 3 || colCount[player][col] == 3 || diagCount[player] == 3 || antiDiagCount[player] == 3) {
                return player == 0 ? "A" : "B";
            }
            
            player = (player + 1) % 2;
        }
        
        return total == 9 ? "Draw" : "Pending";
    }
}