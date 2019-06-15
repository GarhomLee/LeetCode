https://leetcode.com/problems/power-of-two/

// 解法一：iteration/recursion
// 时间复杂度：O(log n)
// 空间复杂度：O(1)

class Solution {
    public boolean isPowerOfTwo(int n) {
        /* corner case */
        if (n <= 0) return false;

        while (n > 1) {
            if (n % 2 != 0) return false;
            n = n / 2;
        }
        return true;
    }
}

// 解法二：利用191. Number of 1 Bits启发的得到1 bit的性质，用Bit manipulation。
// 时间复杂度：O(1)
// 空间复杂度：O(1)

class Solution {
    public boolean isPowerOfTwo(int n) {
        /* corner case */
        if (n <= 0) return false;
        
        return (n & (n - 1)) == 0;
    }
}