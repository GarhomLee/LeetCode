https://leetcode.com/problems/play-with-chips/

// 思路：Math + Greedy
//         根据题意，所有偶数元素可以0成本全部转移到某一特定偶数，奇数元素同理。因此，题目转化为取奇数和偶数元素的个数较小值，
//         这样所用的成本最小。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for (int n: chips) {
            if (n % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        
        return Math.min(odd, even);
    }
}