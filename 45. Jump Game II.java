https://leetcode.com/problems/jump-game-ii/

// 属于greedy method问题。
// 1）corner cases: 如果只有一个元素，那么就不用跳了，直接返回0
// 2）维护3个变量：farthest，表示从当前位置能跳到的最远位置；currEnd，表示上一跳的最远位置，当超过了这个位置，
//     一定会触发下一次跳跃；jump，表示跳的步数。
// 3）遍历数组时，不断更新farthest。
//    注意：数组遍历的范围是[0:nums.length - 2]，【不需要到最后一个数】。
// 4）如果当前位置到达了currEnd，表示这一跳必须结束，下一跳一定触发，所以更新jump，同时currEnd更新为farthest。

class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int farthest = -1, currEnd = 0, jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {  // Attention
            farthest = Math.max(farthest, nums[i] + i);
            if (i == currEnd) {
                jump++;
                currEnd = farthest;
            }
        }
        return jump;
    }
}