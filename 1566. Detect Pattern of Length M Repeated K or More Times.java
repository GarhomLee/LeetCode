https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/

idea: Brute Force
time complexity: O(n*m)
space complexity: O(1)

class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0; i + k*m - 1 < n; i++) {
            boolean isMatch = true;
            for (int j = i; j < i + k*m; j++) {
                isMatch = isMatch && (arr[j] == arr[i + (j - i) % m]);
            }
            
            if (isMatch) {
                return true;
            }
        }
            
        return false;
    }
}