https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

// 总体思路：在（部分排序）数组寻找某类元素，可以用Binary Search。
//         low boundary: matrix的最小值matrix[0][0]
//         high boundary: matrix的最大值[rowLen - 1][colLen - 1]
//         g(m):根据性质，mid越小，matrix中小于等于mid的元素个数count越小，所以要找到最小的mid使得count>=k
//         返回值：临界点的元素low
// 时间复杂度：O(m*n*log(m*n)), m=matrix.length, n=matrix[0].length
// 空间复杂度：O(1)

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int low = matrix[0][0], high = matrix[rowLen - 1][colLen - 1];
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = getLessThan(matrix, mid);
            if (count >= k) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
    /** find how many elements are less than or equal to a given val in matrix */
    private int getLessThan(int[][] matrix, int val) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int count = 0;
        for (int row = 0; row < rowLen; row++) {
            int col = 0;
            while (col < colLen && matrix[row][col] <= val) {
                count++;
                col++;
            }
        }
        return count;
    }
}

// 优化：在求matrix中小于等于mid的的元素个数时，还能用binary search找到每一行中大于mid的最小元素位置，来求小于等于mid的元素个数。
// 时间复杂度：优化至O(m*log(n)*log(m*n)), m=matrix.length, n=matrix[0].length

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        /* binary search using range [lowest:highest] in matrix, ie. low is the lowest and high is the highest number in matrix */
        int low = matrix[0][0], high = matrix[rowLen - 1][colLen - 1];
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = getLE(matrix, mid);
            if (count >= k) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
    
    private int getLE(int[][] matrix, int val) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int count = 0;
        for (int row = 0; row < rowLen; row++) {
            /* optimization: use binary search to find the number of elements less than val in each col */
            int index = binarySearch(matrix[row], target);  // find the index where elements in [index, length) are greater than target, therefore elements in [0:index) are less than target
            count += index;
        }
        
        return count;
    }
    
    private int binarySearch(int[] arr, int target) {
        /* binary search using index [0:length-1] in matrix, ie. low is 0, high is arr.length - 1 */
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}