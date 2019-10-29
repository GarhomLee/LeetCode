https://leetcode.com/problems/maximum-product-of-three-numbers/

// 解法一：Brute Force
// 时间复杂度：O(n^3)
// 空间复杂度：O(1)


// 解法二：Sort + Math
//         直接排序（不需要转绝对值），最大乘积必然出现在最大的三个数，或最大的一个数乘以最小的
//         两个数（因为可能是负数）。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return Math.max(nums[0] * nums[1] * nums[n - 1],
                nums[n - 3] * nums[n - 2] * nums[n - 1]);
    }
}