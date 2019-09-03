https://leetcode.com/problems/paint-house/

// 思路：DP。视频讲解：https://www.youtube.com/watch?v=fZIsEPhSBgM
//         状态函数：costs[i][j]，表示包含房子[0:i]，且第i个房子选第j种颜色时的最小花费。
//         状态转移方程：根据题意，第i个房子选了第j种颜色后，不能跟第i-1个房子同色，因此想要让花费最小，就要在第i-1个房子
//             的除第j种颜色外的其他颜色总花费种选最小值。
//             最后的结果返回costs[n - 1][0], costs[n - 1][1]和costs[n - 1][2]三者的最小值。
//         初始值：第0个house的可选颜色的cost，即costs[0][0:2]
// 时间复杂度：O(n), n=costs.length
// 空间复杂度：O(1)
犯错点：1.思路错误

class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        
        int n = costs.length;
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        
        return Math.min(costs[n - 1][0], Math.min(costs[n - 1][1], costs[n - 1][2]));
    }
}