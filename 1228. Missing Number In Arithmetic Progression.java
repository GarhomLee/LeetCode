https://leetcode.com/problems/missing-number-in-arithmetic-progression/

// 思路：Binary Search (search in index)
//         如果是包含n个数字的完整等差数列，那么差值为(arr[n - 1] - arr[0]) / (n - 1)。
//         由于有1个数缺失，所以用此时的个数n'求出的差值为差值为(arr[n' - 1] - arr[0]) / n'。
//         根据等差数列性质，arr[i] == arr[0] + diff * i。如果中间有缺失值，那么从缺失的位置j开始，
//         会有arr[j] != arr[0] + diff * j。因此，需要找到这样一个位置j，使得从位置j开始的右边所有
//         位置都满足这个不等的条件。
// 时间复杂度：O(1)
// 空间复杂度：O(1)

class Solution {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int diff = (arr[n - 1] - arr[0]) / n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] != arr[0] + diff * mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return arr[0] + diff * low;
    }
}