https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/

idea: Prefix Sum + Math
    -If current sum is even, only consider how many previous sums are odd;
     if current sum is odd, vice versa.
time complexity: O(n)
space complexity: O(1)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int numOfSubarrays(int[] arr) {
        int odd = 0, even = 1;
        int sum = 0, ret = 0;
        for (int num : arr) {
            sum += num;
            if (sum % 2 == 0) {
                ret += odd;
                even++;
            } else {
                ret += even;
                odd++;
            }
            ret %= MOD;
        }
        
        return ret;
    }
}