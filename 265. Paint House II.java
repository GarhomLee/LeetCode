https://leetcode.com/problems/paint-house-ii/

// 对比：256 Paint House的follow-up，将颜色数量从3个变成k个。

// 思路：DP
//         状态函数：costs[i][j]，表示包含房子[0:i]，且第i个房子选第j种颜色时的最小花费。
//         状态转移方程：维护变量preMin1和preMin2，表示包含房子[0:i-1]时最小花费的两个颜色的index，初始值都为-1；
//             min1和min2，表示包含房子[0:i-1]时最小花费的两个颜色的index，初始值都为-1。
//             对于第i个房子，遍历k种颜色所需的花费，根据preMin1和preMin2的信息来得到包含costs[i][j]的最小花费，
//             再加上costs[i][j]后即为包含房子[0:i]且第i个房子选第j种颜色时的最小花费。
//             如果j!=preMin1，那么就选preMin1；否则j==preMin1，根据题意不能选preMin1，只能选preMin2。
//             更新了costs[i][j]后，根据costs[i][j]来更新min1和min2。遍历完第i个房子的k种颜色后，利用min1和min2
//             来更新preMin1和preMin2。
//         初始值：第0个house的可选颜色的cost，即costs[0][0:k]
// 时间复杂度：O(n * k), n = costs.length, k = costs[0].length = num of colors
// 空间复杂度：O(1)

class Solution {
    public int minCostII(int[][] costs) {
        /* base case */
        if (costs.length == 0) {
            return 0;
        }
        
        int n = costs.length, k = costs[0].length;
        int preMin1 = -1, preMin2 = -1;  // indices of two min cost including previous house
        for (int i = 0; i < n; i++) {
            int min1 = -1, min2 = -1;  // indices of two min cost including current house
            
            for (int j = 0; j < k; j++) {
                if (i != 0) {
                    costs[i][j] += j == preMin1 ? costs[i - 1][preMin2] : costs[i - 1][preMin1];
                }
                
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {  // update min1
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {  // update min2
                    min2 = j;
                }
            }
            
            preMin1 = min1;
            preMin2 = min2;
        }
        
        return costs[n - 1][preMin1];
    }
}


二刷：

class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        for (int i = 1; i <= n; i++) {
            int smallestIdx = -1, secondSmallestIdx = -1;
            int smallestCost = Integer.MAX_VALUE, secondSmallestCost = Integer.MAX_VALUE;
            
            for (int j = 0; j < k; j++) {
                if (dp[i - 1][j] < smallestCost) {
                    secondSmallestCost = smallestCost;
                    secondSmallestIdx = smallestIdx;
                    
                    smallestCost = dp[i - 1][j];
                    smallestIdx = j;
                } else if (dp[i - 1][j] < secondSmallestCost) {
                    secondSmallestCost = dp[i - 1][j];
                    secondSmallestIdx = j;
                }
            }
            
            for (int j = 0; j < k; j++) {
                dp[i][j] = costs[i - 1][j] + (j == smallestIdx ? secondSmallestCost : smallestCost);
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int cost: dp[n]) {
            min = Math.min(min, cost);
        }
        
        return min;
    }
}