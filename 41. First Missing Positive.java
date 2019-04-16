https://leetcode.com/problems/first-missing-positive/

// 套用模板。
// 1）遍历数组，将所有元素放到该放的位置，直到放置正确、或位置被已经正确放置的元素占据、或元素为负数为止。
// 2）再次遍历数组，查找第一个缺失的正整数。

class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) return i + 1;
        }
        return nums.length + 1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}