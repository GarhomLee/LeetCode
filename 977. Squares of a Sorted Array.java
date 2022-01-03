https://leetcode.com/problems/squares-of-a-sorted-array/

思路：Two Pointers (left & right)
    在整数数轴（从负数到正数）取平方，结果是先递减再递增。
time comp: O(n)
space comp: O(n)

class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] ret = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int squaredLeft = nums[left] * nums[left], squaredRight = nums[right] * nums[right];
            if (squaredLeft >= squaredRight) {
                ret[i] = squaredLeft;
                left++;
            } else {
                ret[i] = squaredRight;
                right--;
            }
        }
        
        return ret;
    }
}