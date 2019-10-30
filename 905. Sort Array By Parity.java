https://leetcode.com/problems/sort-array-by-parity/

// 思路：Two Pointers (left & right)
//         维护A[0:left)为偶数，A[right+1:length)为奇数。
// 时间复杂度：O(1)
// 空间复杂度：O(1)

class Solution {
    public int[] sortArrayByParity(int[] A) {
        int left = 0, right = A.length - 1;
        while (left <= right) {
            if (A[left] % 2 == 1) {
                swap(A, left, right--);
            } else {
                left++;
            }
        }
        
        return A;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}