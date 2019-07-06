https://leetcode.com/problems/optimal-division/

// 思路：数学问题。详见https://leetcode.com/problems/optimal-division/solution/
//      只需要将括号把从第二个数开始到最后一个数的整个范围括起来，最后得到的结果就是最大的。

class Solution {
    public String optimalDivision(int[] nums) {
        /* corner cases */
        if (nums.length == 0) return "";
        if (nums.length == 1) return nums[0] + "";
        if (nums.length == 2) return nums[0] + "/" + nums[1];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < nums.length; i++) {
            sb.append('/').append(nums[i]);
        }
        sb.append(')');
        sb.insert(1, '(');
        sb.insert(0, nums[0]);
        return sb.toString();
    }
}