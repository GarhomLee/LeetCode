https://leetcode.com/problems/non-decreasing-array/

// 思路：Greedy，根据题意改变某些元素，并统计改变的个数。
//     遍历nums数组，假设nuns[0:i]已经是non decreasing array，如果遇到nums[i] > nums[i+1]，那么可能有两种
//     情况：
//     1）如果nums[i-1] > nums[i+1]，也就是类似[2,3,1]的情况，那么需要将nums[i+1]变成nums[i]，使得nums[0:i+1]
//         也符合题意。
//     2）如果i==0或者nums[i-1] <= nums[i+1]，也就是类似[4,2,3]或者[3,7,5]的情况，那么需要将nums[i]变成nums[i+1]，
//         使得nums[0:i+1]也符合题意。
//     每次进行变换，更新count++。最后返回判断count是否小于等于1.
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.思路错误：不能用Stack维护increasing array，否则[1,2,2,1,3]这样的情况会出错。

class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) continue;
            
            if (i > 0 && nums[i - 1] > nums[i + 1]) {
                nums[i + 1] = nums[i];
            } else {
                nums[i] = nums[i + 1];
            }
            count++;
            
        }
        return count <= 1;
    }
}


其他解法：longest increasing subsequence耐心排序？