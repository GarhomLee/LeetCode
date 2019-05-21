https://leetcode.com/problems/range-sum-query-immutable/

// 总体思路：用一个辅助数组sums，sums[i]储存的是nums[0:i-1]的和，所以要求nums[i]+...+nums[j]，只需要用(nums[0]+...+nums[j])-(nums[0]+...+nums[i-1])，
//         即sums[j+1]-sums[i]。

class NumArray {
    int[] sums;
    public NumArray(int[] nums) {
        sums = new int[nums.length + 1];  // sums[i] indicates the sum of nums[0:i-1], thus sums[0] must be 0
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = nums[i - 1] + sums[i - 1];
        }
    }
    
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */