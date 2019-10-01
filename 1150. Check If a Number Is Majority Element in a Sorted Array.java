https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/

// 解法一：Math
// 时间复杂度：O(1)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：如果nums数组有偶数个元素，那么nums[length / 2]仅仅是右半边的最左边元素，可能只包含右半边
//             length / 2个元素，不符合题意。因此，需要再确认nums[(length - 1) / 2]，即左半边的最右边元素。
//             对于奇数个元素的nums数组，nums[length / 2]和nums[(length - 1) / 2]是实际上同一个元素。

class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        //return nums[nums.length / 2] == target;  // {Mistake 1}
        return nums[nums.length / 2] == target && nums[(nums.length - 1) / 2] == target;  // {Correction 1}
    }
}


解法二：Binary Search
时间复杂度：O(log n)
空间复杂度：O(1)