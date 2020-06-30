https://leetcode.com/problems/maximum-sum-circular-subarray/

idea: Kadane's algo (DP). Referring to: https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length, sum = 0;
        int currMax = 0, currMin = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            currMax = A[i] + Math.max(currMax, 0);
            currMin = A[i] + Math.min(currMin, 0);
            
            max = Math.max(max, currMax);
            min = Math.min(min, currMin);
            // System.out.println("max="+max+",min="+min);
        }
        
        return max > 0 ? Math.max(max, sum - min) : max;
    }
}