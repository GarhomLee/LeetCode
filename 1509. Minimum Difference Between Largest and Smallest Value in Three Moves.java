https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/

idea: Sort
    -The extreme values should be removed from the smallest end or/and largest end.
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 0;
        }
        
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            min = Math.min(min, nums[n - 4 + i] - nums[i]);
        }
        return min;
    }
}