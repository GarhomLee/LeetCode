https://leetcode.com/problems/monotonic-array/

idea: Use 2 variables
time complexity: O(n)
space complexity: O(1)

class Solution {
    public boolean isMonotonic(int[] A) {
        boolean isIncr = true, isDecr = true;
        for (int i = 1; i < A.length; i++) {
            isIncr &= A[i - 1] <= A[i];
            isDecr &= A[i - 1] >= A[i];
        }
        
        return isIncr || isDecr;
    }
}