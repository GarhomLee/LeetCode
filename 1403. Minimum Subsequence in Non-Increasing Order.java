https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/

idea: Sort
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        int sum = 0, curr = 0, n = nums.length;
        Arrays.sort(nums);
        for (int num : nums) {
            sum += num;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0 && curr <= sum / 2; i--) {
            curr += nums[i];
            res.add(nums[i]);
        }
        
        return res;
    }
}