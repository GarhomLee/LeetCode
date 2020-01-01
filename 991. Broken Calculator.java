https://leetcode.com/problems/broken-calculator/

思路：Math + Greedy。参考：https://leetcode.com/problems/broken-calculator/discuss/234484/JavaC%2B%2BPython-Change-Y-to-X-in-1-Line
        将从X经过减和乘得到Y转换成从Y经过除和加得到X。
时间复杂度：O(log Y)
空间复杂度：O(1)

class Solution {
    public int brokenCalc(int X, int Y) {
        int step = 0;
        while (Y > X) {
            Y = Y % 2 == 0 ? Y / 2 : Y + 1;
            step++;
        }
        
        return step + X - Y;
    }
}