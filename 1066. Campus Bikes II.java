https://leetcode.com/problems/campus-bikes-ii/

// 解法一（Intuition）：Backtracking
//         递归函数定义：void dfs(int[][] workers, int wid, int[][] bikes, boolean[] usedBikes, int curr)，
//             表示搜索从wid开始的，已经用了usedBikes的所有可能的安排里能得到的最小距离，当前搜索到的距离之和为curr。
//         终止条件：wid == workers.length，表示所有workers都已经安排了bike，更新全局变量res。
//         递归过程：搜索所有可能的bikes，跳过已经被安排了的bike，把当前第i辆bike安排给当前worker，然后调用dfs()
//             搜索下一个worker。每次dfs()结束都需要重置usedBikes[i]。
// 时间复杂度：O(n * m!), n=workers.length, m=bikes.length
// 空间复杂度：O(m)
// 小幅优化：当curr > res时，后续的加和结果一定比res大，因此可以不需要再继续搜索，直接return。

class Solution {
    int res = Integer.MAX_VALUE;
    
    private int getDist(int[] arr1, int[] arr2) {
        return Math.abs(arr1[0] - arr2[0]) + Math.abs(arr1[1] - arr2[1]);
    }
    
    private void dfs(int[][] workers, int wid, int[][] bikes, boolean[] usedBikes, int curr) {
        // goal
        if (wid == workers.length) {
            res = Math.min(res, curr);
            return;
        }
        // optimization
        if (curr > res) {
            return;
        }

        // choices
        for (int bid = 0; bid < bikes.length; bid++) {
            if (usedBikes[bid]) continue;  // constraints
            
            usedBikes[bid] = true;
            int dist = getDist(workers[wid], bikes[bid]);
            dfs(workers, wid + 1, bikes, usedBikes, curr + dist);
            usedBikes[bid] = false;  // reset
        }
    }
    
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        dfs(workers, 0, bikes, new boolean[m], 0);
        
        return res;
    }
}


// 解法二（优化）：Recursion with Memoization + Bit Manipulation。参考：https://leetcode.com/problems/campus-bikes-ii/discuss/360037/Step-by-Step-solution-from-400ms-to-1ms-(beating-100)
//         递归函数定义：int dfs(int[][] workers, int wid, int[][] bikes, int unusedBikes, int[] dp)，
//             表示如果以unusedBikes的二进制表示当前【还没用过的bike】，当前要安排的worker为wid，将所有还没安排的
//             worker都安排上bike之后的距离的最小值。
//             辅助数组dp[i]定义类似，更强调【还没用过的bike】能得到的距离最小值，初始化为-1。
//         终止条件：wid = workers.length，所有的worker都安排好了，返回0。
//         递归过程：1）dp[unusedBikes]不为-1，说明【给[wid:workers.length]这些worker安排unusedBikes的结果】已经
//                 被计算过，直接返回dp[unusedBikes]。
//             2）对于当前wid，搜索所有可能的bid，根据压缩的状态unusedBikes判断这个bid能否使用。对于所有可以使用的bid，
//                 调用dfs()，加上当前wid和bid的距离，取这些结果的最小值，赋值给dp[unusedBikes]。这就意味着，当unusedBikes
//                 的二进制表示中有i个1，就一定有[0:i-1]这前i个worker已经被安排好了。
// 时间复杂度：O(n), n=workers.length
// 空间复杂度：O(m), m=bikes.length

class Solution {
    
    private int getDist(int[] arr1, int[] arr2) {
        return Math.abs(arr1[0] - arr2[0]) + Math.abs(arr1[1] - arr2[1]);
    }
    
    private int dfs(int[][] workers, int wid, int[][] bikes, int unusedBikes, int[] dp) {
        // base case
        if (wid == workers.length) {
            return 0;
        }
        if (dp[unusedBikes] >= 0) {
            return dp[unusedBikes];
        }
        
        dp[unusedBikes] = Integer.MAX_VALUE;
        for (int bid = 0; bid < bikes.length; bid++) {
            if (((unusedBikes >> bid) & 1) != 0) continue;  // this bike has been assigned
            
            int dist = getDist(workers[wid], bikes[bid]);
            dp[unusedBikes] = Math.min(dp[unusedBikes], dist + dfs(workers, wid + 1, bikes, unusedBikes | (1 << bid), dp));
        }
        
        return dp[unusedBikes];
    }
    
    public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[] dp = new int[1 << m];  // bit compression
        Arrays.fill(dp, -1);
        
        return dfs(workers, 0, bikes, 0, dp);
    }
}