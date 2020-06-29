https://leetcode.com/problems/longest-turbulent-subarray/

idea: DP (cache info)
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxTurbulenceSize(int[] A) {
        int n = A.length, currLen = 1, maxLen = 1;
        long preDiff = 0;
        for (int i = 1; i < n; i++) {
            long currDiff = A[i] - A[i - 1]; 
            if (preDiff * currDiff >= 0) {
                currLen = currDiff == 0 ? 1 : 2;
            } else {
                currLen++;
            }
            maxLen = Math.max(maxLen, currLen);
            preDiff = currDiff;
        }
        
        return maxLen;
    }
}