https://leetcode.com/problems/unique-binary-search-trees/

// 总体思路：是组合数学里Catalan number的应用，用DP来解题。
//         状态函数：dp[i]表示利用i个不同的数字（1-based，不用管具体是哪i个数字）可以得到的unique BST的数量。
//         状态转移方程：对于确定的root，左右子树的范围和node个数都可以确定。对于某一确定个数的所有数字，能形成的BST数量是确定的，如由2个不同的数能形成2个不同的BST（即dp[2]=2），
//                 由3个不同的数能形成5个不同的BST（即dp[3]=5），而不需要确定具体是哪2或3个数字。所以，对于i个不同的数字，可以有i个不同的root；对于每个root，记为k，左右子树的node个数
//                 都可以确定，分别为k-1个和i-k个，且都可以形成的unique BST的数量都已经被计算过作为memoization，分别为dp[k-1]和dp[i-k]。对于每种左子树的排列，都可以形成dp[i-k]种
//                 右子树的排列，所以对于每个root k都有dp[k-1]*dp[i-k]种排列方式，最后把这k种结果相加即为dp[i]。
//                  特殊情况下，左子树或右子树为空，应取值为1，即dp[0]=1。                
//         初始值：dp[0]=1，而dp[0]本身无实际意义；当i>0时dp[i]=0
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public int numTrees(int n) {
        //if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {  // i indicates we are using i distinct numbers in [1:i] to form the BST
            for (int k = 1; k <= i; k++) {  // k indiciates the number we are using as the root of BST
                dp[i] += dp[k - 1] * dp[i - k];  // k-1 indicates in how many numbers in [1:i] are less than k, while i-k indicates in how many numbers in [1:i] are greater than k
            }
        }
        return dp[n];
    }
}