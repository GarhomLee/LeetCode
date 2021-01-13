https://leetcode.com/problems/binary-search/

idea: Binary Search
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low < nums.length && nums[low] == target ? low : -1;
    }
}