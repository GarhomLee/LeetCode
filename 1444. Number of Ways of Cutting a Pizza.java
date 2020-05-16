https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/

idea: DP

time complexity: O(k*rowLen*colLen)
space complexity: O(k*rowLen*colLen)
optimization: use Suffix sum to precompute apple num from [startRow][startCol] to [rowLen-1][colLen-1]

class Solution {
    final int MOD = 1_000_000_007;
    
    private int dfs(char[][] board, int piece, int startRow, int startCol, int apples, int[][][] dp) {
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        if (apples < piece || startRow >= rowLen || startCol >= colLen) {
            return 0;
        }
        if (piece == 1) {
            return 1;
        }
        if (dp[piece][startRow][startCol] != -1) {
            return dp[piece][startRow][startCol];
        }
        
        int res = 0;
        int total = apples;
        for (int row = startRow; row < rowLen; row++) {
            int found = 0;
            for (int col = startCol; col < colLen; col++) {
                found += board[row][col] == 'A' ? 1 : 0;
            }
            total -= found;
            
            if (total == apples) continue;
            
            res += (dfs(board, piece - 1, row + 1, startCol, total, dp) % MOD);
            res %= MOD;
        }
        
        total = apples;
        for (int col = startCol; col < colLen; col++) {
            int found = 0;
                for (int row = startRow; row < rowLen; row++) {
                found += board[row][col] == 'A' ? 1 : 0;
            }
            total -= found;
            
            if (total == apples) continue;
            
            res += (dfs(board, piece - 1, startRow, col + 1, total, dp) % MOD);
            res %= MOD;
        }
        
        res %= MOD;
        dp[piece][startRow][startCol] = res;
        return res;
    }
    
    public int ways(String[] pizza, int k) {
        int rowLen = pizza.length, colLen = rowLen == 0 ? 0 : pizza[0].length();
        char[][] board = new char[rowLen][colLen];
        int total = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                char c = pizza[row].charAt(col);
                board[row][col] = c;
                total += c == 'A' ? 1 : 0;
            }
        }
        
        
        int[][][] dp = new int[k + 1][rowLen][colLen];
        for (int i = 0; i <= k; i++) {
            for (int row = 0; row < rowLen; row++) {
                Arrays.fill(dp[i][row], -1);
            }
        }
        
        return dfs(board, k, 0, 0, total, dp);
    }
}