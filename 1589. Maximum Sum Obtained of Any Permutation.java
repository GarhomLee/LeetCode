https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/

idea: Sweep Line + Sort + Greedy. Refer to: https://leetcode.com/problems/maximum-sum-obtained-of-any-permutation/discuss/854206/JavaC%2B%2BPython-Sweep-Line
    -Assign the large nums to positions with higher frequencies.
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        long res = 0;
        int n = nums.length;
        int[] count = new int[n];
        for (int[] req : requests) {
            count[req[0]]++;
            if (req[1] + 1 < n) {
                count[req[1] + 1]--;
            }
        }
        for (int i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }
        
        Arrays.sort(nums);
        Arrays.sort(count);
        for (int i = 0; i < n; i++) {
            res += ((long) count[i] * nums[i]) % MOD;
            res %= MOD;
        }
        
        return (int) res;
    }
}