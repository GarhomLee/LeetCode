https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

153. Find Minimum in Rotated Sorted Array的follow-up，增加了允许duplicate的条件。

// 解法一：Binary Search
//         因为一定能找到，所以可以用左闭右开的写法
// 时间复杂度：worst: O(n)
// 空间复杂度：O(1)？

class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[high]) high = mid;
            else if (nums[mid] > nums[high]) low = mid + 1;
            else high--;
        }
        return nums[low];
    }
}

// 解法二：Divide and Conquer (Recursion)
//         由于没法直接从边界元素的大小比较判断是否已经排过序，所以最后时间复杂度和直接扫描找最小值差别不大。这是因为允许了duplicate的存在。
// 时间复杂度：worst: O(n)
// 空间复杂度：O(1)？

class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    private int findMin(int[] nums, int low, int high) {
        if (low == high || nums[low] < nums[high]) return nums[low];
        int mid = low + (high - low) / 2;
        return Math.min(findMin(nums, low, mid), findMin(nums, mid + 1, high));
    }
}