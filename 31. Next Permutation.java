https://leetcode.com/problems/next-permutation/

// Two pointers问题。
// 1）left从右往左找到第一个比left + 1小的数（如果是递减数列，那么会是-1）。注意nums[right] 【<=】 nums[left]
// 2）right同样从右往左找到比nums[left]大的数中最小的那个，这时nums[right]为next permutation中第一个和当前的permutaion不同的数
// 3）交换nums[left]和nums[right]
// 4）翻转[left + 1 ... nums.length - 1]，将left和right更新，然后翻转

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;
        
        int left = nums.length - 2;
        while (left >= 0 && nums[left] >= nums[left + 1]) left--;
        
        int right = nums.length - 1;
        if (left >= 0) {
            while (right > left && nums[right] <= nums[left]) right--;
            swap(nums, left, right);
        }

        left++;
        right = nums.length - 1;
        while (left < right) swap(nums, left++, right--);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}