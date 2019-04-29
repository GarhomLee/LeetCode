https://leetcode.com/problems/valid-perfect-square/

// 解法一：Binary search，注意用【lon】g避免越界。

class Solution {
    public boolean isPerfectSquare(int num) {
        if (num <= 0) return false;
        int low = 0, high = num;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid == num) return true;
            else if ((long) mid * mid > num) high = mid - 1;
            else low = mid + 1;
        } 
        return false;
    }
}

// 解法二：牛顿法，背公式。

class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        long x = num / 2;
        while (x * x > num) x = (x + num / x) / 2;
        return x * x == num;
    }
}