https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/

// 思路：只减少奇数或者偶数位置的值，然后取较小值。
//         维护两个变量：even，表示偶数index要减少的值的和；odd，表示奇数index要减少的值的和。
//         遍历nums数组，维护两个临时变量：left表示当前元素nums[i]左边的元素，如果左边没有元素
//         就赋值为MAX_VALUE；right表示当前元素nums[i]右边的元素，如果右边没有元素就赋值为MAX_VALUE。
//         如果是偶数index，那么要比左右两个元素都小需要减少的值取决于left和right的较小值，且最小为0，
//         因为如果已经是比左右两个元素都小就不需要做任何变化。奇数index同理。
//         结果返回even和odd的较小值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int movesToMakeZigzag(int[] nums) {
        if (nums.length < 2) return 0;
        
        int even = 0, odd = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? Integer.MAX_VALUE : nums[i - 1];
            int right = i == nums.length - 1 ? Integer.MAX_VALUE : nums[i + 1];
            if ((i & 1) == 0) {
                even += Math.max(0, nums[i] - Math.min(left, right) + 1);
            } else {
                odd += Math.max(0, nums[i] - Math.min(left, right) + 1);
            }
        }
        
        return Math.min(even, odd);
    }
}