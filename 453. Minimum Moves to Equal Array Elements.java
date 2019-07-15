https://leetcode.com/problems/minimum-moves-to-equal-array-elements/

// 思路：数学问题，找规律。
//      将n-1个元素同时增加1，等价于将1个元素减少1。因此，问题转化为将所有元素按每一步某个元素减少1需要多少步，
//      也就是简单的将每个元素都减少到全局最小元素min，然后所有步数相加。

class Solution {
    public int minMoves(int[] nums) {
        int res = 0, min = Integer.MAX_VALUE;
        /* find min number in nums array */
        for (int n: nums) {
            min = Math.min(min, n);
        }
        /* get sum - n * min */
        for (int n: nums) {
            res += n - min;
        }
        return res;
    }
}