https://leetcode.com/problems/powx-n/

// 二分法+recursion。
// 1）corner cases：n为0或1要单独讨论。
// 2）对于n < 0，只要将x变为1 / x，后续就不需要考虑n的正负。然而，如果n为Integer.MIN_VALUE，直接变为-n会出错，所以需要取巧。
//     trick：因为要用二分法，所以【用变量t记录n / 2】，然后t = -t。在recursion中，【直接用myPow(x, t)】而不用myPow(x, n / 2)。
// 3）维护temp【记录myPow(x, t)，避免重复计算】
// 4）根据n的奇偶性确定需不需要额外再乘一个x。

class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        int t = n / 2;
        if (n < 0) {
            x = 1 / x;
            t = -t;
        }
        double temp = myPow(x, t);
        if (n % 2 == 0) return temp * temp;
        return temp * temp * x;
    }
}