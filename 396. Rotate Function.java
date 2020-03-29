https://leetcode.com/problems/rotate-function/

idea: Math
    Find the mathematical observation in this question
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int maxRotateFunction(int[] A) {
        int sum = 0, funcVal = 0, len = A.length;
        for (int i = 0; i < len; i++) {
            sum += A[i];
            funcVal += i * A[i];
        }
        int max = funcVal;
        
        // observation: next function value is the previous value + sum of all values 
        // substracted by current element (starting from the last one) - len * current element
        for (int i = len - 1; i >= 1; i--) {
            funcVal += (sum - A[i]) - (len - 1) * A[i];
            max = Math.max(max, funcVal);
        }
        return max;
        
    }
}