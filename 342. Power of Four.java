https://leetcode.com/problems/power-of-four/

// 思路：和231. Power of Two类似。观察可知规律：在二进制表示中只有1个1，且必须处在奇数位（从右到左的第一位、第三位、第五位……）。
//      技巧在于利用这个数0x55555555，这个数的二进制表示为1010101010101010101010101010101，即只有奇数位有1.
//      0xaaaaaaaa等一些其他数也和0x55555555等价。

class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) > 0;
    }
}