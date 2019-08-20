https://leetcode.com/problems/partition-equal-subset-sum/

// 对比：518. Coin Change 2，不同点在于本题每个数只能使用1次。
// 思路：DP，属于0/1背包问题。视频讲解：https://www.youtube.com/watch?v=r6I-ikllNDM
//         首先，进行一些预处理。先将nums数组所有元素求和，如果sum不是偶数，可以直接返回false。
//         然后，利用DP解题。
//         状态函数：dp[i][j]表示是否能用nums数组前i个数（即nums[0:i-1]，当前元素为nums[i-1]）组成和为j
//         状态转移方程：对于dp[i][j]的取值，要考虑当前元素nums[i-1]“放还是不放进来”两种情况。
//                     如果不放当前元素，那么dp[i][j]取值和dp[i-1][j]相等，也就是当前元素不产生影响。
//                     如果放当前元素，因为每个元素只能用一次，所以dp[i][j]取值要考虑当前元素放进来之前的dp[i-1][j-nums[i-1]]的情况。
//                     综合两种情况，dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]。
//                     注意，如果j < nums[i - 1]，那么当前元素一定放不进来，所以只有dp[i][j] = dp[i - 1][j]一种情况。
//         初始值：dp[0][0] = true，表示用0个元素是可以得到和为0的。
// 犯错点：1.细节错误：每处理一个新的元素nums[i]，都需要从从上一个元素nums[i-1]继承状态，即dp[i][j] = dp[i-1][j]，
//                 表示如果前i-1个元素能组成和为j，那么前i个元素也必然能组成和为j，也就是nums[i]没有作用。
// 时间复杂度：O(n*sum)
// 空间复杂度：O(n*sum)

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        if ((sum & 1) != 0) return false;
        
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = true;
            for (int j = 1; j <= sum / 2; j++) {
                //dp[i][j] = dp[i - 1][j - nums[i]];  // {Mistake 1}
                if(j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }  // {Correction 1}

                if (dp[i][sum / 2]) return true;  // check the target
            }
        }
        
        return false;
    }
}

// 优化：空间降维，因为dp[i]只和dp[i - 1]有关，所以可以利用一维数组将空间复杂度降到O(sum)

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        if ((sum & 1) != 0) {
            return false;
        }
        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum / 2; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
                if (dp[sum / 2]) {
                    return true;
                }
            }
        }
        
        return false;
    }
}