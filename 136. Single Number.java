https://leetcode.com/problems/single-number/

// 经典的Bit Manipulation题，用到的是XOR。也可以用Hash Table。

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n: nums) {
            res = res ^ n;
        }
        return res;
    }
}