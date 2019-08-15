https://leetcode.com/problems/knight-probability-in-chessboard/

// 对比：935. Knight Dialer

// 思路：Recursion with Memoization
//         观察题目可以发现，题目实际上还是求从位置[r][c]开始，经过K次跳跃后能留在N*N棋盘的路径总数。只需要
//         将路径总数/(8^K)就是最后要求的概率。然而，根据数据规模可以发现，当K==100时，8^K=10^90，即使用long
//         也存不下。因此，可以在每一步都求解概率，这样结果就是double。
//         递归函数定义：double dfs(int N, int K, int row, int col, Double[][] dp)表示从N*N棋盘的
//                 [row][col]跳K步后能留在棋盘上的概率。
//                 辅助数组dp定义相同。
//         终止条件：1）跳出棋盘，返回概率0
//                 2）K==0，在没有掉出棋盘的情况下没有剩余步数，那么一定在棋盘上，返回概率1
//                 3）dp[index][K] != null，已经求解过，直接返回
//         递归过程：将二维坐标降维到一维。
//                 遍历8个可以走的方向，递归求解下一步开始的概率dfs(N, K - 1, nextRow, nextCol, dp)，
//                 而从当前位置[row][col]到下一个位置[nextRow][nextCol]的概率为1/8，那么这条路径的概率就是
//                 1 / 8 * dfs(N, K - 1, nextRow, nextCol, dp)，注意下一步的概率有可能为0。
//                 最后dp[index][K]为八个方向的概率之和。
// 时间复杂度：O(N^2 * K)
// 空间复杂度：O(N^2 * K)，可优化降维至O(N^2)
// 犯错点：1.Java特性错误：如果声明变量为Double，那么不能直接将primitive int转换成Class Double，需要用
//             Double.valueOf()。

class Solution {
    int[] dir = new int[]{-1, 2, -1, -2, 1, 2, 1, -2, -1};
    
    public double knightProbability(int N, int K, int r, int c) {
        Double[][] dp = new Double[N * N][K + 1];
        return dfs(N, K, r, c, dp);
    }
    
    private double dfs(int N, int K, int row, int col, Double[][] dp) {
        /* base case */
        if (row < 0 || row >= N || col < 0 || col >= N) {  // out of boundary, the probability of staying on the desk is 0
            return Double.valueOf(0); 
        }
        if (K == 0) {  // no more move, P(stay) = 1
            return Double.valueOf(1); 
        }
        
        int index = row * N + col;  // dimension reduction
        if (dp[index][K] != null) {  // use memoization
            return dp[index][K];
        }
        
        dp[index][K] = Double.valueOf(0);  // 
        for (int i = 0; i < dir.length - 1; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            dp[index][K] += dfs(N, K - 1, nextRow, nextCol, dp) / 8;
        }
        
        return dp[index][K];
    }
}