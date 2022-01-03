https://leetcode.com/problems/rotate-array/

思路1：用额外的copy数组
time comp: O(n)
space comp: O(n)

class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) {
            return;
        }
        
        int[] copy = Arrays.copyOf(nums, n);
        for (int newIdx = 0, currIdx = n - k;
             newIdx < n;
             newIdx++, currIdx = (currIdx + 1) % n) {
            nums[newIdx] = copy[currIdx];
        }
    }
}