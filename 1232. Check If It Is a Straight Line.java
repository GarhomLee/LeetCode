https://leetcode.com/problems/check-if-it-is-a-straight-line/

// 思路：Math
//         利用第三个点和两个基准点分别构成的直线的斜率是否相同来判断，即判断(y_curr - y_1) / (x_curr - x_1)
//         是否等于(y_curr - y_2) / (x_curr - x_2)。为了避免除数为0，可以采用乘法的形式，即判断
//         (y_curr - y_1) * (x_curr - x_2)是否等于(y_curr - y_2) * (x_curr - x_1)。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int[] p1 = coordinates[0], p2 = coordinates[1];
        for (int[] curr : coordinates) {
            if ((curr[1] - p1[1]) * (curr[0] - p2[0]) != (curr[1] - p2[1]) * (curr[0] - p1[0])) {
                return false;
            }
        }
        
        return true;
    }
}