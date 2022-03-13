https://leetcode.com/problems/sort-transformed-array/

idea1: Sort
time comp: O(n log n)


idea2: Math + Two Pointers. Refer to: https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution
time comp: O(n)

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int left = 0, right = nums.length - 1;
        int[] ret = new int[nums.length];
        int idx = a >= 0 ? nums.length - 1 : 0;
        // rearrange nums
        while (left <= right) {
            if (a >= 0) {
                ret[idx] = cal(a, b, c, nums[left]) >= cal(a, b, c, nums[right]) ? nums[left++] : nums[right--];
                idx--;
            } else {
                ret[idx] = cal(a, b, c, nums[left]) >= cal(a, b, c, nums[right]) ? nums[right--] : nums[left++];
                idx++;
            }
        }
        
        // do calculation
        for (int i = 0; i < ret.length; i++) {
            ret[i] = cal(a, b, c, ret[i]);
        }
        return ret;
    }
    
    private int cal(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }
}