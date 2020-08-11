https://leetcode.com/problems/missing-element-in-sorted-array/

// 思路：Binary Search (in index)
//         观察到每两个元素之间的missing number个数的累加和是递增的，那么可以对这些累加和进行binary search，
//         找到第k个missing number所在的元素范围。
// 时间复杂度：O(n + log n) = O(n)
// 空间复杂度：O(n)

class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int[] gaps = new int[n];
        for (int i = 1; i < nums.length; i++) {
            gaps[i] = gaps[i - 1] + (nums[i] - nums[i - 1] - 1);
        }
        
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (gaps[mid] >= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return nums[low - 1] + (k - gaps[low - 1]);
    }
}


优化：观察到gaps[i] = nums[i] - nums[0] - i，可以把时间复杂度优化到O(log n)，空间复杂度优化到O(1)


二刷：类似1539. Kth Missing Positive Number
class Solution {
    public int missingElement(int[] arr, int k) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] - arr[0] - mid >= k) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        int startNum = arr[0] + low - 1;
        return startNum + k;
    }
}