https://leetcode.com/problems/delete-and-earn/

类比：198. House Robber

思路：Dynamic Programming，转换成198. House Robber。视频讲解：https://www.youtube.com/watch?v=YzZd-bsMthk
        状态函数定义：dp[i]表示当搜索到数i时能得到的分数和最大值，不论i取或不取。本题利用count数组
            先存数字i出现的次数，再用作dp数组。
        初始值：count[0] = 0 * count[0] = 0;
                count[1] = 1 * count[1] = count[1];
        状态转移方程：count[i]先更新为i * count[i]，然后取count[i - 2] + count[i]和count[i - 1]
            的较大值。
时间复杂度：O(m + n), n=nums.length, m=max
空间复杂度：O(m)

class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }
        
        
        for (int i = 2; i <= max; i++) {
            count[i] *= i;
            count[i] = Math.max(count[i - 2] + count[i], count[i - 1]);
        }
        
        return count[max];
    }
}