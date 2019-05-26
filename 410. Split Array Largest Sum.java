https://leetcode.com/problems/split-array-largest-sum/

// 解法一：Top-down Memoization
        // 注意dp数组的定义：dp[i][j]表示将nums[0:j)分为所有可能i组时的所有可能的subarray最大sum的最小值（很绕）。
        // 首先用sums数组，sums[i]表示nums[0:i)的和。
        // 用helper method，用recursion来求最后的答案。对于某个分组数量m，可以分为两部分：前m-1组和最后一组。用recursion
        // 得到了前m-1组的largest sum后，跟最后一组的sum比较，取较大值，作为当前m分组的一个可能的最大sum。由于有多个可能的分组，
        // 利用不同的分界点k可以得到不同的分组方式。在所有分组方式求得的最大sum中取最小的一个，就是当前m分组在nums[0:j)所有可能的
        // subarray最大sum的最小值。
        // 终止条件：m>end，即分组数比数组元素多，直接返回0；m==1，只有一个分组，直接返回sums[end]。

class Solution {
    public int splitArray(int[] nums, int m) {
        int[] sums = new int[nums.length + 1];  // sums[i] indicates the sum of [0:i)
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[m + 1][nums.length + 1];  // dp[i][j] indicates the minimum largest sum of [0:j) among i subarrays
        return split(nums, m, nums.length, dp, sums);
    }
    
    /** helper method to find the minimum largest sum of [0:end) among m subarrays */
    private int split(int[] nums, int m, int end, int[][] dp, int[] sums) {
        /* termination conidtions */
        if (m > end) return 0;  // That the number of subarrays is greater than the number of the element in the array makes no sense
        if (m == 1) return sums[end];

        /* use memoization */
        if (dp[m][end] > 0) return dp[m][end];

        dp[m][end] = Integer.MAX_VALUE;
        for (int k = 1; k < end; k++) {  // at split point k, divide nums[0:k) into m-1 subarrays and nums[k:end) as the last subarray, thus m subarrays in total
            dp[m][end] = Math.min(dp[m][end], Math.max(split(nums, m - 1, k, dp, sums), sums[end] - sums[k]));
        }
        return dp[m][end];
    }
}

// 解法二：Bottom-up DP
//         状态函数：dp[m][end]表示将nums[0:end)分为所有可能m组时的所有可能的subarray最大sum的最小值（很绕）。
//         状态转移方程：对于某个分组数量i，可以分为两部分：前m-1组和最后一组。利用dp数组中存储的前i-1组在nums[0:k)
//             的largest sum后，跟最后一组的sum比较，取较大值，作为当前i分组的一个可能的最大sum。由于有多个可能的分组，
//             利用不同的分界点k可以得到不同的分组方式。在所有分组方式求得的最大sum中取最小的一个，就是当前m分组在nums[0:end)
//             所有可能的subarray最大sum的最小值。
//         初始值：i==1，只有一个分组，dp[1][end]=sums[end]; i>=2时dp[i][end]=Integer.MAX_VALUE。前i-1组的分界点k左边
//             至少有i-1个元素，所以k从i-1开始，end-1为止，右边也至少要有一个元素
// 时间复杂度：O(m*n^2), m=the number of subarays, n=nums.length
// 空间复杂度：O(m*n), m=the number of subarays, n=nums.length
// 犯错点：1.在划分不同组时，可以划分的组数最大应为m，而不是nums.length

class Solution {
    public int splitArray(int[] nums, int m) {
        int[] sums = new int[nums.length + 1];  // sums[i] indicates the sum of [0:i)
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[m + 1][nums.length + 1];  // dp[i][j] indicates the minimum largest sum of [0:j) among i subarrays
        
        for (int end = 1; end <= nums.length; end++) {  // processing the minimum largest sum in nums[0:end)
            dp[1][end] = sums[end];  // 1 subarray means the whole array itself
            //for (int i = 2; i <= end; i++) // {Mistake 1: the most subarrays we can divide should be m instead of end which is related to nums.length}
            for (int i = 2; i <= m; i++) {  // divide nums[0:end) into i subarrays
                                            // {Correction 1}
                dp[i][end] = Integer.MAX_VALUE;
                for (int k = i - 1; k < end; k++) {  // use the results from nums[0:k) with i-1 subarrays and compare this result with sums[k:end)
                    dp[i][end] = Math.min(dp[i][end], Math.max(dp[i-1][k], sums[end] - sums[k]));
                }
            }
        }
        
        return dp[m][nums.length];
    }
}

// 解法三：Greedy+Binary Search
//         很巧妙的做法。观察到minimum largest sum (mls)和分组数k的关系：k越大（eg.k==nums.length），mls越小；k越小（eg.k==1），mls越大。
//         low boundary: k==nums.length时nums中最大的那个元素
//         high boundary: k==1时所有nums元素的总和sums[nums.length]
//         判断条件g(m): 根据当前分组给定的可能最大和mid，“贪心地”找到分组数k（将每个subarray尽可能填满），比较k和题目给定的m。目标是找到最小的mid，
//             使得跟mid有关的k满足m>=k，因为mid越小，k越大
//         返回值：low，即临界点的minimum largest sum
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int splitArray(int[] nums, int m) {
        int[] sums = new int[nums.length + 1];  // sums[i] indicates the sum of [0:i)
        int maxNum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
            maxNum = Math.max(maxNum, nums[i - 1]);
        }
        
        int low = maxNum, high = sums[nums.length];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            /* determine the number of subarrays (k) with each sum less than or equal to mid, the larger the mid, the smaller the k */
            
            int k = groupCount(nums, mid);
            /* find the minimum mid s. t. g(f(mid)) = g(k) = m - k >= 0 is true */
            if (m >= k) high = mid - 1;
            else low = mid + 1;
        }
        
        return low;
    }
    /** determine how many subarrays can be divided if the max sum of each subarray is maxSum */
    private int groupCount(int[] nums, int maxSum) {
        int k = 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > maxSum) {
                k++;
                sum = 0;
            }
            sum += nums[i];
        }
        return k;
    }
}