https://leetcode.com/problems/sqrtx/

// 解法一：牛顿法，背公式

class Solution {
    public int mySqrt(int x) {
        long sqrt = x;
        while (sqrt * sqrt > x) {
            sqrt = (sqrt + x / sqrt) / 2;
        }
        return (int) sqrt;
    }
}


// 解法二：二分法

class Solution {
    public int mySqrt(int x) {
        long low = 0, high = x;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid <= x && ((mid + 1) * (mid + 1) > x)) return (int) mid;
            else if (mid * mid > x || mid * mid > Integer.MAX_VALUE) high = mid - 1;
            else if ((mid + 1) * (mid + 1) <= x) low = mid + 1;
        }
        return -1;
    }
}

// 解法三：用内置的Math.sqrt()