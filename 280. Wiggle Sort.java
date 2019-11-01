https://leetcode.com/problems/wiggle-sort/

// 思路：Greedy
//         维护变量boolean isLess表示当前状态，如果isLess==true表示当前nums[i]应该要小于等于nums[i+1]，
//         否则nums[i]应该要大于等于nums[i+1]。
//         假设所有nums[0:i-1]已经满足题意，如果isLess==true，但nums[i]>nums[i+1]，那么需要将它们调换。
//         调换以后可以得到合理的结果，是因为已知nums[i-1]>=nums[i]，那么一定有nums[i-1]>nums[i+1]，所以
//         调换后一定能满足nums[i-1]>=nums[i]<nums[i+1]。
//         对于isLess==false时同理。
// 时间复杂度：O(1)
// 空间复杂度：O(1)

class Solution {
    public void wiggleSort(int[] nums) {
        boolean isLess = true;
        for (int i = 0; i + 1 < nums.length; i++) {
            if (isLess) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            isLess = !isLess;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}