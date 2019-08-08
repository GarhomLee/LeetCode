https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

// 对比：思路和435. Non-overlapping Intervals完全一样，代码和返回结果略有不同

// 思路：Greedy，返回最大的non-overlapping intervals的个数。
// 时间复杂度：O(n log n)
// 时间复杂度：O(1)

class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[1] - p2[1];
            }
        });
        
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                count++;
                end = points[i][1];
            }
        }
        
        return count;
    }
}