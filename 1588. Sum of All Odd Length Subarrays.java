https://leetcode.com/problems/sum-of-all-odd-length-subarrays/

idea: Prefix Sum
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + arr[i - 1];
        }
        
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j -= 2) {
                res += sum[i] - sum[j];
            }
        }
        
        return res;
    }
}