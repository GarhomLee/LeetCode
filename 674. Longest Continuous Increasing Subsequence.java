https://leetcode.com/problems/longest-continuous-increasing-subsequence/

// 思路：常规遍历，维护变量：len，记录以nums[i]结尾的subarray长度；maxLen，全局最长长度。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int len = 0, maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            len = i > 0 && nums[i] > nums[i - 1] ? len + 1 : 1;
            maxLen = Math.max(maxLen, len);
        }
        
        return maxLen;
    }
}