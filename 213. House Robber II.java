https://leetcode.com/problems/house-robber-ii/

// 总体思路：198. House Robber的follow-up，将数组头和尾连起来形成circle。在这种情况下，头和尾一定不能同时取值，所以可以将头部去掉重新变成链状，
//         或者把尾部去掉重新变成链状。那么，就会得到两个可能的最大收益值，然后取二者较大值即可。
//         状态函数：dpNoTail[i]表示到达nums数组（去掉最后一个元素）的第i个数（1-based，可简化代码）时能获得的最大收益，同理dpNoHead表示到达nums数组（去掉第一个元素）的第i个数（1-based，可简化代码）时能获得的最大收益
//         状态转移方程：dpNoTail[i]根据取和不取nums[i]，跟dpNoTail[i-2]+nums[i]和dpNoTail[i-1]有关，取二者较大值。dpNoHead[i]同理。
//         初始值：dpNoTail[i] = 0, dpNoHead[i] = 0
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 注意：1.由于dpNoTail和dpNoHead两个数组的定义，如果nums数组长度为1，就会无法得到正确值，所以应该对长度为1的nums数组特殊讨论，直接返回nums[0]
//       2.对于dpNoTail数组，因为i从1开始，所以当i<2时要对dpNoTail[i-2]特殊处理；而对于dpNoHead数组，i从2开始，所以不需要特殊处理

class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];  // Attenion 1: when length is 1, both dpNoTail[nums.length-1] and dpNoHead[nums.length] will not have correct answer, because the for loop will not be executed and they will both be 0s instead of nums[0].
        
        int[] dpNoTail = new int[nums.length], dpNoHead = new int[nums.length + 1];  // Trick: the size of dpNoHead array is length+1 so that it maintains 1-based
        for (int i = 1; i < nums.length; i++) {
            dpNoTail[i] = Math.max(dpNoTail[i - 1], nums[i - 1] + (i >= 2? dpNoTail[i - 2] : 0));
        }
        for (int i = 2; i <= nums.length; i++) {
            dpNoHead[i] = Math.max(dpNoHead[i - 1], nums[i - 1] + dpNoHead[i - 2]);  // Attention 2: i starts from 2, thus no need to consider i < 2
        }
        return Math.max(dpNoTail[nums.length - 1], dpNoHead[nums.length]);
    }
}