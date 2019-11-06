https://leetcode.com/problems/set-mismatch/

// 思路：Find Missing/Duplicate Number类型题模版。
// 时间复杂度：O(n)?
// 空间复杂度：O(1)

class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
            
            if (nums[i] != i + 1) {
                res[0] = nums[i];
                res[1] = i + 1;
            }
        }
        
        return res;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}