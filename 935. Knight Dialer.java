https://leetcode.com/problems/knight-dialer/

// 对比：688. Knight Probability in Chessboard

// 思路：Top-down Recursion with Memoization，视频讲解：https://www.youtube.com/watch?v=HTnIFivp0aw
//         Trick: 根据题目性质，维护二维数组dir，其中dir[num]表示从数字num可以跳到的下一个数字的集合。如
//         dir[1]={6,8}，表示从数字1可以跳到数字6或8。注意dir[5]为空，表示从数字5不能跳到任何其他的数字。
//         递归函数定义：int dfs(int num, int move, Integer[][] dp)表示从键盘数字num开始跳，还剩move步
//                 可以跳，所能得到的所有数字组合的总个数。
//                 dp[num][move]定义相同。
//         终止条件：1）move==0，不能再跳，能得到的只有当前数字，所以返回个数1
//                 2）dp[num][move] != null，已经求解过结果，直接返回dp[num][move]
//         递归过程：遍历和当前数字num对应的下一个数字nextNum，递归求解dfs(nextNum, move - 1, dp)的结果。
//                 因此，dp[num][move]为所有nextNum结果的总和。
// 时间复杂度：O(N)
// 空间复杂度：O(N)
// 犯错点：1.数据溢出错误：要记得每一步都要用MOD取余

class Solution {
    int[][] dir = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{4,2}};
    int MOD = 1000000007;
    
    public int knightDialer(int N) {
        int res = 0;
        Integer[][] dp = new Integer[10][N];
        /* try all start numbers */
        for (int i = 0; i <= 9; i++) {
            res += dfs(i, N - 1, dp);
            res %= MOD;
        }
        
        return res;
    }
    
    private int dfs(int num, int move, Integer[][] dp) {
        /* base case */
        if (move == 0) {
            return 1;
        }
        if (dp[num][move] != null) {
            return dp[num][move];  // use memoization
        }
        
        dp[num][move] = 0;
        for (int nextNum: dir[num]) {  // try all next step
            dp[num][move] += dfs(nextNum, move - 1, dp);
            // {Mistake 1}
            dp[num][move] %= MOD;  // {Correction 1}
        }
        
        return dp[num][move];
    }
}


// Generalization: 遍历所有八个可能的下一步格子，同时将二维坐标转为一维
// 时间复杂度：O(N)
// 空间复杂度：O(N)

class Solution {
    int[] dir = new int[]{-1, 2, -1, -2, 1, 2, 1, -2, -1};
    int ROW_LEN = 4;
    int COL_LEN = 3;
    int MOD = 1000000007;
    
    public int knightDialer(int N) {
        int res = 0;
        Integer[][] dp = new Integer[10][N];
        for (int i = 0; i <= 9; i++) {
            int row = i == 9 ? 3 : i / COL_LEN;
            int col = i == 9 ? 1 : i % COL_LEN;
            res += dfs(row, col, N - 1, dp);
            res = res % MOD;
        }
        
        return res;
    }
    
    private int dfs(int row, int col, int move, Integer[][] dp) {
        if (row < 0 || row >= ROW_LEN || col < 0 || col >= COL_LEN || (row == 3 && (col == 0 || col == 2))) {
            return 0;
        }
        if (move == 0) {
            return 1;
        }
        
        int index = (row == 3 && col == 1) ? 9 : row * COL_LEN + col;
        if (dp[index][move] != null) {
            return dp[index][move];
        }
        
        dp[index][move] = 0;
        for (int i = 0; i < dir.length - 1; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            dp[index][move] += dfs(nextRow, nextCol, move - 1, dp);
            dp[index][move] %= MOD;
        }
        
        return dp[index][move];
    }
}


// 解法二：Bottom-up DP
// 时间复杂度：O(N)
// 空间复杂度：O(N)

class Solution {
    int[][] dir = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{4,2}};
    int MOD = 1000000007;
    
    public int knightDialer(int N) {
        int res = 0;
        int[][] dp = new int[N][10];
        Arrays.fill(dp[0], 1);
        
        for (int move = 1; move < N; move++) {
            for (int num = 0; num <= 9; num++) {
                for (int pre: dir[num]) {
                    dp[move][num] += dp[move - 1][pre];
                    dp[move][num] %= MOD;
                }
            }
        }
        
        for (int i = 0; i <= 9; i++) {
            res += dp[N - 1][i];
            res %= MOD;
        }
        
        return res;
    }
    
}