https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// 总体思路：用Binary Search找lower bound和upper bound的问题，左开右闭
//         low boundary: index=0
//         high boundary: index=nums.length
//         g(m):对于找lower bound，需要找到最小的mid index满足nums[mid] >= target，即第一个大于等于target的数；
//             对于找upper bound，需要找到最小的mid index满足nums[mid] > target，即第一个比target大的数，不取等
//         返回值：对于找lower bound，找到的low就是lower bound的位置，所以返回low（或-1）；
//             对于找upper bound，找到的low是upper bound的位置，即第一个比target大的数，所以返回low-1（或-1）；
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) return new int[]{-1, -1};
        int[] res = new int[2];
        res[0] = findLow(nums, target, 0, nums.length);
        res[1] = findHigh(nums, target, 0, nums.length);
        return res;
    }

    /** find lower bound of target in nums array */
    private int findLow(int[] nums, int target, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) high = mid;  // lower bound 
            else low = mid + 1;
        }
        return nums[low] == target ? low : -1;
    }

    /** find upper bound of target in nums array */
    private int findHigh(int[] nums, int target, int low, int high) {
      while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) high = mid;  // upper bound
            else low = mid + 1;
        }
        return nums[low - 1] == target ? low - 1 : -1;
    }
}