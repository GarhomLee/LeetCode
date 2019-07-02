https://leetcode.com/problems/array-partition-i/

// 思路：找规律。
//      因为需要组成pair，同时使得所有pair的较小数的加和最大，那么就需要使得每个pair的较小数都要比较大，换言之
//      和它配对的大值要比较小。对于所有数中的最小值min，最合适配对的就是剩下的数当中的最小值。以此类推，可知就是
//      将nums数组排序，然后相邻元素两两配对，求所有较小值的和。

class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}