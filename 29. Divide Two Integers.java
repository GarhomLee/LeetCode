https://leetcode.com/problems/divide-two-integers/

// 考察数学的基础，不用乘、除、模运算来计算除法，所以需要【还原成加减法】。

// 解法一：recursion
// 1）corner cases：dividend == 0，直接返回0
// 2）记录最后结果的正负号
// 3）把dividend和divisor都取绝对值进行运算，转换成long的形式以防过界。注意：【取绝对值之前就要转换成long】，否则取绝对值时可能过界
// 4）调用helper method来计算。
// 5）在helper method中，所有参数和返回值都是【long】
// 6）corner cases：ldividend < ldivisor，计算结果一定是0，也是作为recursion的结束条件
// 7）维护变量sum，表示用上了的除数的和；维护变量quotient，表示用上了的除数的个数（即商）
// 8）注意while循环中的等号：如果不加等号，空间复杂度为O(log n)；【加了等号，空间复杂度可以小于O(log n)】
// 9）sum += sum而不是sum += ldivisor，是一种优化的方式，将时间复杂度从O(n)降为O(log n)。同时quotient += quotient而不是quotient += 1
// 10）由于sum += sum会漏掉一些数，所以需要递归处理ldividend - sum，然后返回quotient + 递归结果的和
// 11）根据lres是否越界，sign的取值，转换成int后返回最终结果

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;

        int sign = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0) ? -1 : 1;
        long ldividend = Math.abs((long) dividend), ldivisor = Math.abs((long) divisor);
        
        long lres = divide(ldividend, ldivisor);
        if (lres > Integer.MAX_VALUE) return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int res = (int) lres;
        return sign == 1 ? res : -res;
    }
    private long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor, quotient = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            quotient += quotient;
        }
        return quotient + divide(ldividend - sum, ldivisor);
    }
}

// 解法二：iteration
// 将recursion写成另一层while循环。注意count的初始化，totalCount的更新。

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0 || Math.abs((long) dividend) < Math.abs((long) divisor)) return 0;  // {Mistake 1: should convert int to long before getting absolute value}
        int sign = ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) ? -1 : 1;
        long totalCount = 0;
        long ldividend = Math.abs((long) dividend), ldivisor = Math.abs((long) divisor);  // {Mistake 1: should convert int to long before getting absolute value}
        
        while (ldividend >= ldivisor) {
            long currdivisor = ldivisor;
            int count = 0;
            while (ldividend >= currdivisor) {
                count = count == 0 ? 1 : count * 2;
                ldividend -= currdivisor;
                currdivisor += currdivisor;
                totalCount += count;
            }
        }
        long res = sign * totalCount;
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;  // {Mistake 2: if result is greater than Integer.MAX_VALUE, it will cause overflow}
    }
}

// 解法三：用Bit manipulation，将sum += sum（也就是sum *= 2）的操作变为【sum << 1】，quotient同理。