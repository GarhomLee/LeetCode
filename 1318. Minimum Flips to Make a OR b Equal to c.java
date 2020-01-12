https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/

思路：Bit Manipulation
        只有当a和b的某个位置的bit和c对应位置的bit不相同时，才需要flip。有两种情况：
        1）c的bit为1，而a和b的bit进行或运算结果为0，那么只要flip 1个bit即可。
        2）c的bit为0，而a和b的bit进行或运算结果为1，那么需要flip的bit的个数等于a和b中1 bit的个数。
        每次搜索完，将a，b和c右移一位。
时间复杂度：O(32) = O(1)
空间复杂度：O(1)

class Solution {
    public int minFlips(int a, int b, int c) {
        int count = 0;
        while ((a | b) != c) {
            int alast = a & 1, blast = b & 1, clast = c & 1;
            if ((alast | blast) != clast) {
                count += clast == 1 ? 1 : alast + blast;
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }
        
        return count;
    }
}