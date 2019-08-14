https://leetcode.com/problems/cherry-pickup/

// 思路：Recursion with Memoization。视频讲解：https://www.youtube.com/watch?v=vvPSWORCKow
//         初始化dp数组为Integer.MIN_VALUE。
//         递归函数定义：int dfs(int row1, int col1, int row2, int[][][] dp)，表示从两个点[row1][col1]
//             和[row2][col2]【同步走左上走到[0][0]】【且两个人都能到达[0][0]】所能捡到的最大樱桃数量，同一个
//             格子的樱桃只能被一个人捡起。如果是-1则表示不能同时走到[0][0]。其中col2 = row1 + col1 - row2。
//             辅助函数dp[row1][col1][row2]定义相同。
//             观察题目可以发现，从左上角走到右下角再走回左上角，每一趟的步数是一样，因此有两个特性：
//             1）可以转化为两个人同时从右下角走到左上角（也可以从左上角走到右下角，此处选前者）
//             2）两个人的总步数相同，且当前坐标有相互关系，不相互独立。关系为row1 + col1 == row2 + col2
//             因此，只需要维护3维的辅助数组，因为最后一个维度是不必要的，可以根据3个值计算出来。
//         终止条件：1）超过左边界或上边界，返回-1
//                 2）某一个人所处的格子grid[i][j]为-1，说明不能同时走得通，返回-1
//                 3）走到了目标位置grid[0][0]，返回grid[0][0]
//                 4）如果dp[row1][col1][row2]不等于初始值Integer.MIN_VALUE，说明已经搜索过，直接返回
//                     dp[row1][col1][row2]
//         递归过程：step1: 首先，【先搜索接下来的四个方向：同时向左走一步，同时向上走一步，A走左B走上，和
//                         A走上B走左】，比较求出能得到的最大樱桃数量。
//                         如果这四个方向都不能走通，即最大樱桃数量返回-1，那么从当前位置也不可能走通，所以
//                         【当前dp[row1][col1][row2]记录为-1并返回】。
//                         为什么要先搜索下一步的四个方向？因为这样才能唯一确定是否出现无解（即-1）的情况。
//                         如果先捡起樱桃，再搜索下一步的四个方向，那么-1的信息可能会被“淹没”掉。
//                 step2: 如果能走通，那么先捡起[row1][col1]的樱桃。如果[row1][col1]和[row2][col2]
//                         不是同一个点，那么也捡起[row2][col2]的樱桃。
//                         当前dp[row1][col1][row2]为当前两个点得到的樱桃数量加上下一步四个方向的最大
//                         樱桃数量的总和。
// 时间复杂度：O(n^3)
// 空间复杂度：O(n^3)
// 犯错点：1.越界错误：要同时保证row1，col1，row2，col2同时在界内。即使有公式col2 = row1 + col1 - row2，
//             也不能保证col2在界内。
//         2.细节错误：如果搜索完4个方向都不能走，那么在返回当前结果为不能走的-1前，要先将dp值赋值为-1，表明
//             当前dp[row1][col1][row2]已经计算过。

class Solution {
    int[][] grid;
    
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        int n = grid.length;
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        int res = dfs(n - 1, n - 1, n - 1, dp);
        return res < 0 ? 0 : res;
    }
    
    private int dfs(int row1, int col1, int row2, int[][][] dp) {
        int col2 = row1 + col1 - row2;
        //if (row1 < 0 || col1 < 0 || row2 < 0 || col2 < 0 || grid[row1][col1] == -1 || grid[row2][col2] == -1)  // {Mistake 1}
        if (row1 < 0 || col1 < 0 || row2 < 0 || col2 < 0 || grid[row1][col1] == -1 || grid[row2][col2] == -1) {  // {Correction 1}
            return -1;
        }
        if (row1 == 0 && col1 == 0) {
            return grid[row1][col1];
        }
        if (dp[row1][col1][row2] != Integer.MIN_VALUE) {
            return dp[row1][col1][row2];
        }
        
        
        int ans = Math.max(Math.max(dfs(row1 - 1, col1, row2 -1, dp), dfs(row1, col1 - 1, row2, dp)),
                          Math.max(dfs(row1 - 1, col1, row2, dp), dfs(row1, col1 - 1, row2 - 1, dp)));
        if (ans == -1) {
            // {Mistake 2}
            dp[row1][col1][row2] = ans;  // {Correction 2}
            return ans;
        }
        
        ans += grid[row1][col1];
        if (row1 != row2) {
            ans += grid[row2][col2];
        }
        
        dp[row1][col1][row2] = ans;
        return dp[row1][col1][row2];
    }
}


优化：DP，空间可以降维