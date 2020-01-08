https://leetcode.com/problems/valid-triangle-number/

思路：Binary Search
        单调递增的三个数a,b,c需要满足c-b<a<b+c。由于单调递增，a<b+c必然符合，因此只需要找到a使得
        条件c-b<a满足。
时间复杂度：O(n^2 * log n)
空间复杂度：O(n) for sort

class Solution {
    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length, res = 0;
        for (int i = 1; i + 1 < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int index = binarySearch(nums, 0, i - 1, nums[j] - nums[i] + 1);
                res += (i - index);
            }
        }
        
        return res;
    }
}