https://leetcode.com/problems/super-egg-drop/

// Brute Force：Recursion with Memoization
// 出现的问题：时间复杂度O(K*N^2)，超时


// 解法一：DP + Binary Search
// 时间复杂度：O(K*N log N)，实际运行时间很长（为什么？）
// 空间复杂度：O(K*N)


// 解法二：Recursion with Memoization + Binary Search，参考：https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
//         递归函数定义：int dfs(int K, int N, int[][] dp)，表示给定K个鸡蛋，找到N层楼中的分割点楼层F，
//                 在最坏情况下需要的最小次数，即Minimax。
//                 辅助数组dp定义相同。
//         终止条件：1）K==1，只有一个鸡蛋，那么最坏情况下的次数等于楼层数，返回N
//                 2）N==0，不需要尝试了，返回0
//                 3）dp[K][N] > 0，已经求解过结果，返回该结果
//         递归过程： 对于[1:N]的所有可能分割点p，从p楼扔出，都有两种情况：
//                 1）如果蛋碎了，那么分割点楼层F在p之下，那么需要的最小尝试次数为dfs(K-1, p-1, dp)
//                 2）如果没有碎，那么分割点楼层F在p之上，而蛋没有碎还能接着利用，所以那么需要的最小尝试
//                     次数为dfs(K, N-p, dp)。注意，给定某个K，分割点楼层在楼层[p+1:N]和在楼层[1:p]
//                     所需的最小次数没有本质区别，只要楼层数是相同的。
//                 比较naive的解法是，对于[1:N]尝试所有的分割点，时间复杂度O(K*N^2)，超时，需要优化。
//                 可以观察到，函数dfs(K, N, dp)对于N是递增函数，也就是说，【dfs(K-1, p-1, dp)对于p是
//                 递增函数，对于确定的N来说dfs(K, N-p, dp)对p是递减函数】。因此，必定存在位于中间某个
//                 位置的p是递增函数和递减函数的交点，这个交点就是所要求的minimax。【在这个交点左侧，
//                 dfs(K-1, p-1, dp) < dfs(K, N-p, dp)；在这个交点右侧，dfs(K-1, p-1, dp) > dfs(K, N-p, dp)】。
//                 因此，可以使用binary search来找到这个交点。
// 时间复杂度：O(K*N log N)
// 空间复杂度：O(K*N)

class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return dfs(K, N, dp);
    }
    
    private int dfs(int K, int N, int[][] dp) {
        if (K == 1) {
            return N;
        }
        if (N == 0) {
            return 0;
        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        
        int res = Integer.MAX_VALUE;
        int low = 1, high = N;
         while (low <= high) {
            int mid = low + (high - low) / 2;
            int left = dfs(K - 1, mid - 1, dp);
            int right = dfs(K, N - mid, dp);
            res = Math.min(res, 1 + Math.max(left, right));
            if (left == right) {
                break;
            } else if (left > right) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }      
        
        dp[K][N] = res;
        return res;
    }
}


// 解法三：另一种思路的DP，参考：https://leetcode.com/problems/super-egg-drop/discuss/158974/C%2B%2BJavaPython-2D-and-1D-DP-O(KlogN)
//         改变了dp数组的定义，用一个不一样的方式来找到最小尝试次数。
//         状态函数：dp[move][i]，表示给定move次尝试和i个蛋，【最多能检查多少个楼层】。因此，结果返回的是
//                 当dp[move][K] >= N时的次数move。
//         状态转移方程：对于当前给定的move次尝试和i个蛋，从某一楼层扔出鸡蛋，有两种可能：
//                 1）蛋碎了，那么分割点楼层F在此楼层之下，接下来最多能检查dp[move - 1][i - 1]个楼层
//                 2）蛋没碎，还能接着用，那么分割点楼层F在此楼层之上，接下来最多能检查dp[move - 1][i]个楼层
//                 综合两种情况，一共能检查dp[move - 1][i - 1] + dp[move - 1][i] + 1个楼层，这个+1是
//                 表示当前的楼层。
//         初始值：无特殊初始值
// 时间复杂度：O(log N * K)
// 时间复杂度：O(N * K)
// 犯错点：1.细节错误：dp数组的范围要给到[0:N]，而不能只是[0:N-1]，因为K==1时可能会出现move==N的情况。

class Solution {
    public int superEggDrop(int K, int N) {
        //int[][] dp = new int[N][K + 1];
        int[][] dp = new int[N + 1][K + 1];
        int move = 0;
        while (dp[move][K] < N) {
            move++;
            for (int i = 1; i <= K; i++) {
                dp[move][i] = dp[move - 1][i - 1] + dp[move - 1][i] + 1;
            }
        }
        
        return move;
    }
}