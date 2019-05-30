https://leetcode.com/problems/search-in-rotated-sorted-array/

// 总体思路：不一定找得到，用左闭右闭写法。
//         先确定nums[mid]和nums[low]的关系，再确定哪半边有可能已经排好序了，再更新high和low
// 时间复杂度：O(log n)
// 空间复杂度：O(1)
// 犯错点：1.nums[mid]要跟当前window的边界nums[low]比，不要和nums[0]比
//         2.如果假定nums[low:mid]排好序，那么判断条件nums[mid] >= nums[low]要取等，否则会漏掉mid==low的情况
//         3.先假定排好序的区间，然后根据target是否在这个排好序的区间来决定更新high还是low

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) return mid;
            
            /* 写法一：【只】跟nums[low]比较 */
            if (nums[mid] >= nums[low]) {  // {Mistake 1: DO NOT compare nums[mid] with nums[0], because it is nums[low] which is the boundary of current window}
                                           // {Mistake 2: equal sign should be included when we assume nums[low:mid] is sorted, otherwise low==mid will be missed}
                if (nums[low] <= target && target < nums[mid]) high = mid - 1;  // {Mistake 3: the sorted window nums[low:mid] should be determined first, and then put target into comparison}
                else low = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }
            
            /* 写法二：【只】跟nums[high]比较 */
            /*if (nums[mid] <= nums[high]) {
                if (target > nums[mid] && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            } else  {
                if (target < nums[mid] && target >= nums[low]) high = mid - 1;
                else low = mid + 1;
            }*/

        return -1;
    }
}