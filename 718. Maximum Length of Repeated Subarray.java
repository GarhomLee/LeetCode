https://leetcode.com/problems/maximum-length-of-repeated-subarray/

// 解法一：Dynamic Programming (Bottom-up)
//         状态函数定义：dp[i][j]表示以A[i-1]结尾的所有子数组和以B[j-1]结尾的所有子数组
//             能构成的最长repeated subarray长度。
//         初始值：1）dp[0][j] = 0，即A的子数组长度为0时最长repeated subarray长度一定为0
//                 2）dp[i][0] = 0，即B的子数组长度为0时最长repeated subarray长度一定为0
//         状态转移方程：对于当前数字A[i-1]和B[j-1]，有2种情况：
//                 1）A[i-1] == B[j-1]，说明repeated subarray长度可以增加，因此利用
//                     dp[i - 1][j - 1] + 1来更新dp[i][j]
//                 2）A[i-1] != B[j-1]，说明不能形成有效的repeated subarray，那么
//                     dp[i][j] = 0。
//                 在进行DP的过程中，利用dp[i][j]更新maxLen。
// 时间复杂度：O(M * N)
// 空间复杂度：O(M * N)

class Solution {
    public int findLength(int[] A, int[] B) {
        int len1 = A.length, len2 = B.length;
        int maxLen = 0;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            // dp[i][0] = 0;
            for (int j = 1; j <= len2; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        
        return maxLen;
    }
}


解法二：Binary Search (search in index or length)。参考：https://leetcode.com/problems/maximum-length-of-repeated-subarray/solution/
时间复杂度：O((M + N) * min(M, N) * log(min(M, N)))
空间复杂度：O(M^2)
