https://leetcode.com/problems/out-of-boundary-paths/

// 思路：Recursion with Memoization
//         递归函数定义：int dfs(int rowLen, int colLen, int row, int col, int move, Integer[][][] dp)
//                 表示在rowLen*colLen大小的矩阵，从位置[row][col]出发，利用至多move次移动能到达边界的方法总数。
//                 辅助数组dp[move][row][col]定义相同。
//         终止条件：1）[row][col]越界，说明从上一步走1步就能出界，返回1
//                 2）move==0，因为已知1）不满足，所以不可能有办法出界，返回0
//                 3）dp[move][row][col]已经被搜索过，非空，直接返回dp[move][row][col]
//         递归过程：如果最多能走move次，那么只有4种可以走的选择，也就是4个相邻格子，走过去以后都会减少1步。
//                 因此，dp[move][row][col]就是4个相邻格子的dp[move-1]的和。如果遇到出界，或者值为0，
//                 那么就会提前终止，不会用完move次移动。
// 时间复杂度：O(m*n*N)
// 空间复杂度：O(m*n*N)
// 犯错点：1.细节错误：不能用dp[move][row][col] > 0来判断是否已经搜索过了，因为如果搜索过且确定不能到达边界，那么
//             也会有dp[move][row][col] == 0。因此，需要将dp设为Integer数组，通过判断是否为null来确定有没有
//             搜索过。
//         2.数据范围错误：因为数据量可能会很大，因此需要在【每一步都取余数】。

class Solution {
    int[] dir = new int[]{0, -1, 0, 1, 0};
    final int MOD = 1000000007;
    
    public int findPaths(int m, int n, int N, int i, int j) {
        //int[][][] dp = new int[N + 1][m][n];
        Integer[][][] dp = new Integer[N + 1][m][n];
        return dfs(m, n, i, j, N, dp);
    }
    
    private int dfs(int rowLen, int colLen, int row, int col, int move, Integer[][][] dp) {
        /* base cases */
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen) {
            return 1;
        }
        if (move == 0) {
            return 0;
        }
        //if (dp[move][row][col] > 0)  // {Mistake 1}
        if (dp[move][row][col] != null) {  // {Correction 2}
            return dp[move][row][col];  // use memoization
        }
        
        dp[move][row][col] = 0;
        for (int i = 0; i < 4; i++) {  // go over all its adjacent cells
            dp[move][row][col] += dfs(rowLen, colLen, row + dir[i], col + dir[i + 1], move - 1, dp);
            // {Mistake 2}
            dp[move][row][col] %= MOD;  // {Correction 2}
        }
        
        return dp[move][row][col];  // set memoization
    }
}