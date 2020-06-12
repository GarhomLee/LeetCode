https://leetcode.com/problems/leftmost-column-with-at-least-a-one/

idea: Binary Search
time complexity: O(n log n)
space complexity: O(1)

/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    private int binarySearch(BinaryMatrix binaryMatrix, int row, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (binaryMatrix.get(row, mid) >= 1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
    
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int rowLen = dimensions.get(0), colLen = dimensions.get(1);
        int res = colLen;
        for (int row = 0, minCol = colLen - 1; row < rowLen; row++) {
            int col = binarySearch(binaryMatrix, row, 0, minCol);
            minCol = Math.min(minCol, col); // curr min col
            
            if (col < colLen) {
                // update res only when col is valid
                res = Math.min(res, minCol);
            }
        }
        
        return res == colLen ? -1 : res;
    }
}