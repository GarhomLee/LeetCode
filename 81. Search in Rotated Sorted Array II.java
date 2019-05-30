https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

// 总体思路：33. Search in Rotated Sorted Array的follow-up，增加了允许duplicate元素的条件，使得不能直接通过和nums数组边界的比较来得知排序情况。
//         跟33. Search in Rotated Sorted Array相比，在假定排好序的部分序列时，要严格判断nums[mid] > nums[low]或nums[mid] < nums[low]。
//         否则，当nums[mid]==nums[low]时，因为已经知道arget!=nums[mid]，所以nums[low]可以排除，low++。
// 时间复杂度：worst O(n), average O(log n)
// 空间复杂度：O(1)
// 犯错点：1.从头到尾只跟某一边界比较，要么左边界，要么右边界

class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) return true;
            
            /* 写法一：【只】跟nums[low]比较 */
            
            if (nums[mid] > nums[low]) {
                if (target < nums[mid] && target >= nums[low]) high = mid - 1;
                else low = mid + 1;
            } 
            //else if (nums[mid] < nums[high])  // {Mistake 1: comparison should be only against one boundary, either low or high}
            else if (nums[mid] < nums[low]) {  // {Correction 1}
                if (target > nums[mid] && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            } else {
                low++;
            }

            /* 写法二：【只】跟nums[high]比较 */
            /*if (nums[mid] < nums[high]) {
                if (nums[mid] < target && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            } else if (nums[mid] > nums[high]) {
                if (nums[low] <= target && target < nums[mid]) high = mid - 1;
                else low = mid + 1;
            } else {
                high--;
            }*/

        }
        return false;
    }
}