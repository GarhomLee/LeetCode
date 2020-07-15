https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/

idea: Sort
time complexity: O(n^2 log(n^2))
space complexity: O(n^2)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] sum = new int [n*(n+1)/2];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int curr = 0;
            for (int j = i; j >= 0; j--) {
                curr += nums[j];
                sum[k++] = curr;
            }
        }
        
        Arrays.sort(sum);
        int ret = 0;
        for (int i = left - 1; i < right; i++) {
            ret += sum[i] % MOD;
        }
        return ret;
    }
}