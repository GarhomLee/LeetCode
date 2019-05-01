https://leetcode.com/problems/factorial-trailing-zeroes/

// 要找n!的结果中有多少个0在末尾，根据数学性质，只有2*5的乘积才会有0，所以问题转化为有多少个2*5。又因为2一定是足够的，所以只要计算有多少个5即可。

class Solution {
    public int trailingZeroes(int n) {
        return n == 0? 0 : n / 5 + trailingZeroes(n / 5);
    }
}