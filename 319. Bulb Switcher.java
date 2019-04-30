https://leetcode.com/problems/bulb-switcher/

// 一个数的因子个数代表了它在会被turn on/off的次数，每个因子代表它在第几轮被turn on/off。因此，只有被操作了奇数次，它才能为on。
// 一般一个数都有偶数个因子（如2->[1,2], 5->[1,5], 10->[1,2,5,10]），除了完全平方数，有奇数个因子（如4->[1,2,4],9->[1,3,9]），因此这个问题【转化为在小于n的数中有多少个完全平方数】。

// 解法一：iteration

class Solution {
    public int bulbSwitch(int n) {
        if (n <= 1) return n;
        int count = 1;
        for (int i = 2; i <= n / 2; i++) {
            if ((long) i * i <= n) count++;
        }
        return count;
    }
}

// 解法二：直接用内置的Math.sqrt function。

class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}