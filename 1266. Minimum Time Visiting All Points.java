https://leetcode.com/problems/minimum-time-visiting-all-points/

// 思路：Math，找规律。
//     实际上转化为连续两点间曼哈顿距离的横向距离和纵向距离的差值的绝对值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length, res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.max(Math.abs(points[i][0] - points[i - 1][0]), Math.abs(points[i][1] - points[i - 1][1]));
        }
        
        return res;
    }
}