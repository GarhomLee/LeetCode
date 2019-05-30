https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

// 总体思路：用了Binary Search模版。
//         因为nums数组没有duplicate元素，所以当nums数组旋转后，最小的元素一定在中间，且比nums[0]小，所以g(m)为nums[mid] < nums[0]，【不能取等】。
//         特殊情况，nums数组没有旋转，要单独讨论。
// 时间复杂度：O(log n)
// 空间复杂度：O(1)
// 犯错点：1.g(m)为nums[mid] < nums[0]，即找到最小的mid位置使得大于等于mid的位置mid'有nums[mid'] < nums[0]成立。
//         不能取等，否则mid如果是位置0就会出错，如nums=[2,1]。
//        2.nums[mid]不能和nums[low]比，一定要和nums[0]比，因为要找的是nums[0:length]的全局最小，所以边界始终是ums[0]和nums[length]

class Solution {
    public int findMin(int[] nums) {
        if (nums[0] <= nums[nums.length - 1]) return nums[0];  // corner case, special case where nums array is not rotated
        
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            /* 写法一 */
            //if (nums[mid] <= nums[0]) high = mid - 1;  // {Mistake 1}
            //if (nums[mid] < nums[low]) high = mid - 1;  // {Mistake 2}
            if (nums[mid] < nums[0]) high = mid - 1;  // {Correction 1, 2}
            else low = mid + 1;

            /* 写法二：甚至不用判断nums[0] <= nums[nums.length - 1] */ 
            /*if (nums[mid] <= nums[nums.length - 1]) high = mid - 1;
            else low = mid + 1;*/
        }
        return nums[low];
    }
}

/*注意：如果写成左闭右开，又可以跟nums[high]比较了？！！！
        因为一定能找到，所以可以写成左闭右开。
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < nums[nums.length - 1]) high = mid;
            else low = mid + 1;
        }
        return nums[low];
    }
}*/


// 解法二：Divide and Conquer (Recursion)
//         将数组分成两半，利用至少有一半已经排好序的性质，求两半部分各自最小值，然后取较小的一个。
// 时间复杂度：O(log n)
// 空间复杂度：O(1)？

class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    private int findMin(int[] nums, int low, int high) {
        if (low == high || nums[low] < nums[high]) return nums[low];
        int mid = low + (high - low) / 2;
        return Math.min(findMin(nums, low, mid), findMin(nums, mid + 1, high));
    }
}