https://leetcode.com/problems/find-peak-element/

// 总体思路：852. Peak Index in a Mountain Array的follow-up，同样可以用Binary Search模版。
//         还是常规g(m)，关键点在于理解题意。因为只需要找到其中一个peak，而且题目规定任意nums[i]!=nums[i+1]，
//         所以只要满足nums[mid] > nums[mid + 1]条件的mid都可以返回。

class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) high = mid - 1;
            else low = mid + 1;
        }
        return low < nums.length ? low : nums.length - 1;
    }
}