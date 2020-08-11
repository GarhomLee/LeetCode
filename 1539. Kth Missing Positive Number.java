https://leetcode.com/problems/kth-missing-positive-number/
Similar to 1060. Missing Element in Sorted Array.

solution1: Brute Force
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length, curr = 1;
        for (int i = 0; i < n; i++) {
            while (curr < arr[i] && k > 0) {
                curr++;
                k--;
            }
            
            if (k == 0) {
                return curr - 1;
            }
            
            curr++;
        }
        
        return curr + k - 1;
    }
}


solution2: Binary Search. Refer to: https://leetcode.com/problems/kth-missing-positive-number/discuss/779999/JavaC%2B%2BPython-O(logN)
time complexity: O(log n)
space complexity: O(1)

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] - (mid + 1) >= k) {    // calculate missing elem count in [1:arr[mid]]
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        int existingCount = low;
        return existingCount + k;
    }
}