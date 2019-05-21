https://leetcode.com/problems/longest-increasing-subsequence/

// 解法一：DP
//        状态函数：dp[i]表示包含nums[i]在内的increasing subsequence的长度
//        状态转移方程：nums[i]可以加在nums[0:i-1]当中所有比nums[i]小的数后面，所以需要取其中最大的长度+1，即dp[i] = max(dp[i], dp[j] + 1)
//        初始值：dp[i]=1，至少有一个数nums[i]
// 时间复杂度：O(n^2), n = nums.length
// 空间复杂度：O(n), n = nums.length

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];  // dp[i] indicates the length of increasing subsequence including nums[i]
        //dp[0] = 1;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}

// 解法二：耐心排序
//         维护tails数组，tails[i]表示长度为i的increasing subsequence的末尾元素，且需要保持为在所有同样长度的increasing subsequence中这个末尾元素是最小的。
//         根据这个性质，如果nums[i]比tails[0]还要小，更新tails[0]为nums[i]；如果nums[i]比tails[maxLen - 1]还要大，那么可以组成新的长度的increasing subsequence，
//         即tails[maxLen] = nums[i]，同时更新maxLen；如果处于中间某个元素，那么需要用【二分查找】，找到某个tails[index]为nums[i]的ceiling，更新之。这里可以
//         直接使用Arrays.binarySearch()。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.如果将tails[0]初始化，那么要注意nums为空数组的情况
//         2.Arrays.binarySearch()的使用，如果找不到跟key匹配的元素，返回的是-(insert position) - 1，而insertion position是比key大的最小的元素位置（即ceiling的位置）

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;  // {Mistake 1: when length is 0, there will be no tails[0]}
        
        int[] tails = new int[nums.length];
        //tails[0] = nums[0];
        int maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int index = Arrays.binarySearch(tails, 0, maxLen, nums[i]);
            if (index < 0) index = -index - 1;  // {Mistake 2: when the key is not found by Arrays.binarySearch(), it returns -(insert position) - 1, and this should be converted back to a positive number}
            tails[index] = nums[i];
            if (index == maxLen) maxLen++;
        }
        return maxLen;
    }
}