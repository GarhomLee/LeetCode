https://leetcode.com/problems/search-insert-position/

// 总体思路：Binary Search模版，左闭右开
//         low boundary: index=0
//         high boundary: index=nums.length
//         g(m):找到最小mid index使得满足nums[mid] >= target
//         返回值：low index

class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}