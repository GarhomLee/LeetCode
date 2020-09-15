https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/

idea: Sliding Window. Refer to: https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/discuss/830416/Java-Increasing-From-Left-Right-and-Merge-O(n)
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }
        if (left == n - 1) {
            return 0;
        }
        
        int right = n - 1;
        while (left < right && arr[right - 1] <= arr[right]) {
            right--;
        }
        
        int minLen = Math.min(n - left - 1, right);
        int i = 0, j = right; 
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                minLen = Math.min(minLen, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        
        return minLen;
    }
}