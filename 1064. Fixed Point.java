https://leetcode.com/problems/fixed-point/

idea: Binary Search
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int fixedPoint(int[] A) {
        int n = A.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] >= mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low < n && A[low] == low ? low : -1;
    }
}