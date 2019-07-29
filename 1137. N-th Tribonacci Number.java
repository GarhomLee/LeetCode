https://leetcode.com/problems/n-th-tribonacci-number/

// 思路：Fibonacci数列的进阶版，DP降维至常数个向量，用temp临时保存t2的值。

class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int t0 = 0, t1 = 1, t2 = 1;
        for (int i = 2; i < n; i++) {
            int temp = t2;
            t2 += t1 + t0;
            t0 = t1;
            t1 = temp;
        }
        return t2;
    }
}