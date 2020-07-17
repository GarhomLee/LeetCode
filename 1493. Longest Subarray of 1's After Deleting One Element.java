https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/

solution1: Sliding Window
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length, maxLen = 0, zeroCount = 0;
        
        for (int left = 0, right = 0; right < n; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (left <= right && zeroCount > 1) {
                if (nums[left++] == 0) {
                    zeroCount--;
                }
            }
            
            maxLen = Math.max(maxLen, right - left);    // Either only one 0, or all 1s. There must be an element to delete
        }
        
        return maxLen;
    }
}


solution2: DP
time complexity: O(n)
space complexity: O(n)