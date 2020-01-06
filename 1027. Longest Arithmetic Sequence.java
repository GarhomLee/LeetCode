https://leetcode.com/problems/longest-arithmetic-sequence/

思路：DP + Hash Table
        状态函数定义：dp[i][j]表示以A[j]为结尾的、差值为A[j]-A[i]的最长arithmetic sequence长度。
        初始值：dp[i][j] = 2，表示任意两个数都至少能形成元素个数为2的arithmetic sequence。
        状态转移方程：对于每个i，搜索所有[i+1:len]的j，看是否在[0:i-1]存在k使得A[i]-A[k]==A[j]-A[i]。
            如果存在这样的k，那么dp[i][j] = max(dp[i][j], dp[k][i] + 1)。
            由于可能存在多个相同的A[k]值，因此只需要记录搜索过的最右边的A[k]值，得到对应的dp[A[k]][i]即可。
            因此，可以用HashMap来进行优化，key为出现过的A[k]，value为最近出现的位置。
时间复杂度：O(n^2)
空间复杂度：O(n^2)

class Solution {
    public int longestArithSeqLength(int[] A) {
        int len = A.length, max = 0;
        int[][] dp = new int[len][len];
        Map<Integer, Integer> index = new HashMap<>();
        for (int second = 0; second + 1 < len; second++) {
            for (int third = second + 1; third < len; third++) {
                dp[second][third] = 2;
                int diff = A[third] - A[second];
                if (index.containsKey(A[second] - diff)) {
                    int first = index.get(A[second] - diff);
                    dp[second][third] = dp[first][second] + 1;
                }
                max = Math.max(max, dp[second][third]);
            }
            index.put(A[second], second); // update its most recent position
        }
        
        return max;
    }
}