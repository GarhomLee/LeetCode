// https://leetcode.com/problems/dungeon-game/

// 经典的反向DP题。需要从右下角遍历到左上角。
// 1）维护二维dp数组，dp[i][j]表示到达第i行第j列dungeon【之前】所需要的最小生命值，意思是dp[i][j] - dungeon[i][j]是走到[i][j]的时候剩下的生命值，不能低于1，
//     所以【dp[i][j]不能低于Math.max(1, 1 - dungeon[i][j])】。
// 2）初始化分两步。第一步：初始化最后一个元素dp[rowLen - 1][colLen - 1]；第二步，初始化最后一行dp，只跟其右边的元素有关，最低不能低于1。
// 3）从倒数第二行倒数第二列开始遍历，用变量right表示从如果走右边所需的最低生命值，down表示走下边所需最低生命值，均不能低于1，然后取较小值。
// 4）最后返回dp[0][0]，表示【进入房间[0][0]前】所需的最低生命值。

// 优化：dp数组或许可以降到一维，因为只和右边和下边的元素有关。

class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int rowLen = dungeon.length, colLen = rowLen == 0? 0: dungeon[0].length;
        int[][] dp = new int[rowLen][colLen];
        
        /* last element */
        dp[rowLen - 1][colLen - 1] = Math.max(1, 1 - dungeon[rowLen - 1][colLen - 1]);
        
        /* last row */
        for (int col = colLen - 2; col >= 0; col--) dp[rowLen - 1][col] = Math.max(dp[rowLen - 1][col + 1] - dungeon[rowLen - 1][col], 1);
        
        for (int row = rowLen - 2; row >= 0; row--) {
            dp[row][colLen - 1] = Math.max(dp[row + 1][colLen - 1] - dungeon[row][colLen - 1], 1);
            for (int col = colLen - 2; col >= 0; col--) {
                int right = Math.max(dp[row][col + 1] - dungeon[row][col], 1);
                int down = Math.max(dp[row + 1][col] - dungeon[row][col], 1);
                dp[row][col] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }
}