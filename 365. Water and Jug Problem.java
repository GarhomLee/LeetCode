https://leetcode.com/problems/water-and-jug-problem/

// 需要利用最大公约数(greatest common divisor)的性质，具体分析见discuss：https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        
        return z % gcd(x, y) == 0;
    }
    private int gcd(int m, int n) {
        if (n == 0) return m;
        return gcd(n, m % n);
    }
}