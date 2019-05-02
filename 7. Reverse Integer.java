https://leetcode.com/problems/reverse-integer/

// 可以作为integer reversion的模板。
// 1）corner cases：判断是否为0，是就直接返回
// 2）记录sign，然后把x取绝对值。注意需要【转换成long】防止过界
// 3）维护reverse，每次循环时，reverse先自乘10，然后加上当前copy的%10结果，作为reverse的更新，增加一位数。同时copy /= 10来减少最后的一位数。
// 4）根据是否过界、sign的情况返回相应的值

class Solution {
    public int reverse(int x) {
        if (x == 0) return 0;
        int sign = x > 0? 1 : -1;
        long reverse = 0, copy = Math.abs((long) x);
        while (copy > 0) {
            reverse = reverse * 10 + copy % 10;
            copy /= 10;
        }
        if (reverse > Integer.MAX_VALUE) return 0;
        return (int) (reverse * sign);
    }
}