https://leetcode.com/problems/peak-index-in-a-mountain-array/

// 思路：Binary Search模版
//     g(m): 由于确定有peak，且0<peak<A.length-1，所以要找到位置mid使得A[mid] > A[mid + 1]使得大于mid的所有数都成立。

class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int low = 0, high = A.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] > A[mid + 1]) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}