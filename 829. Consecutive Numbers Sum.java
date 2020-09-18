https://leetcode.com/problems/consecutive-numbers-sum/

idea: Math. Refer to: https://leetcode.com/problems/consecutive-numbers-sum/discuss/128959/JavaPython-3-5-liners-O(N-0.5)-Math-method-w-explanation-and-analysis.
time complexity: O(sqrt(N))
space complexity: O(1)

class Solution {
    public int consecutiveNumbersSum(int N) {
        // Let x is the initial num and should not be 0, k is the count of consecutive nums.
        // N = x + (x + 1) + (x + 2) + ... + (x + k - 1) 
        //   = (x + (x + k - 1)) * k / 2
        //   = kx + (k * (k - 1)) / 2
        // Thus, we can only get a result if N - k * (k - 1) / 2 is a multiple of k, given x 
        // cannot be 0.
        int res = 0;
        for (int k = 1; N - (k * (k - 1)) / 2 > 0; k++) {
            if ((N - (k * (k - 1) / 2)) % k == 0) {
                res++;
            }
        }
        
        return res;
    }
}