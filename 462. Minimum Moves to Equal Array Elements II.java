https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/

// 思路：数学问题，453. Minimum Moves to Equal Array Elements的follow-up。
//      找到中数，求各数和中数的【绝对差值】，将所有差值求和即可。
//      为什么选择中数：见https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/94932/Why-median-is-better-than-average
// 时间复杂度：O(n log n)
// 空间复杂度：O(1)
// 犯错点：1.思路错误：求的应该是绝对差值
// 优化：利用Quick Select求中数，可以将时间复杂度降至O(n)

class Solution {
    public int minMoves2(int[] nums) {
        if (nums.length < 2) return 0;
        
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int res = 0;
        for (int n: nums) {
            //res += median - n;  // {Mistake 1}
            res += Math.abs(median - n);  // {Correction 1: absolute difference is necessary}
        }
        return res;
    }
}