https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/

idea: Sort + Sliding Window. Referring to: https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/discuss/709227/JavaC%2B%2BPython-Two-Sum
time complexity: O(n log n)
space complexity: O(n) for sorting

class Solution {
    final int MOD = 1_000_000_007;
    
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, n = nums.length;
        int left = 0, right = n - 1;
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1 ; i < n ; ++i)
            pows[i] = pows[i - 1] * 2 % MOD;
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                res += pows[right - left] % MOD;
                res %= MOD;
                left++;
            }
        }
        
        return res;
    }
}