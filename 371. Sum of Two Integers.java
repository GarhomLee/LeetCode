https://leetcode.com/problems/sum-of-two-integers/

// 思路：Bit Manipulation，视频详解：https://www.youtube.com/watch?v=qq64FrA2UXQ
//      利用3个操作：
//      1）&，找到a和b相加后出现carry的所有位置，也就是a和b都有1的bit
//      2）<<，因为用&找到的carry不是下一步要加的位置，正确的位置应该是左移一位。eg.0111和0111相加，用&找到的carry为0111，但实际上是<<1后的1110
//      利用&和<<找到的是下一步的carry，所以临时存在nextCarry中。
//      3）^，实际执行的相加操作，会出现carry的地方（都为1）就会变成0。结果存在a中。
//      然后更新b为nextCarry，当b（即nextCarry）为0时，表示下一步没有carry了，操作可以停止。最后返回a。

class Solution {
    public int getSum(int a, int b) {
        int nextCarry = 0;
        while (b != 0) {
            nextCarry = (a & b) << 1;
            a = a ^ b;
            b = nextCarry;
        }
        return a;
    }
}