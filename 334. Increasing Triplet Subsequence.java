https://leetcode.com/problems/increasing-triplet-subsequence/

// 思路：Greedy。视频讲解：https://www.youtube.com/watch?v=xV_AS08-OeA
//         维护两个变量：min1，表示当前范围nums[0:i]的最小元素，初始值为MAX_VALUE；min2，表示当前范围
//         nums[0:i]的第二小元素，初始值为MAX_VALUE。
//         注意：如果存在min2且不为初始值，即使min2出现的位置在min1左边（如[2,3,1,5]，nums[i]=5时，min1=1，min2=3），
//         也不影响min2的定义，因为【必定存在一个数在min2左边，且它的值比min2小】，只有这样min2才会被更新。
//         nums[i]可能有三种情况：
//         1）nums[i] < min1，更新min1为nums[i]。（如[5,3,1]，只有min1一直被更新，min2仍然是初始值）
//         2）nums[i] > min1 && nums[i] < min2，更新min2为nums[i]
//         3）nums[i] > min2，因为已知有一个数小于min2，所以找到了符合题意的结果，返回true。
//         实际上【只需要当前nums[i] > min2】即可返回true。
//         如果遍历完都没有结果，返回false。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n < min1) {
                min1 = n;
            } else if (n > min1 && n < min2) {
                min2 = n;
            } else if (n > min2) {
                return true;
            }
        }
        
        return false;
    }
}