https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/

idea: Sort
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }
}