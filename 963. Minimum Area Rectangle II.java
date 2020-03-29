https://leetcode.com/problems/minimum-area-rectangle-ii/

idea: Math. Referring to video: https://www.youtube.com/watch?v=Q-yBwSX7JOk
time complexity:O(n^3)
space complexity: O(n)

class Solution {
    /* find the minimum area given two points with index i and j */
    private double findMin(int[][] points, int i, int j, Map<Integer, Set<Integer>> map) {
        int len = points.length;
        double minArea = Double.MAX_VALUE;
        for (int k = 0; k < len; k++) {
            if (k == i || k == j) continue;

            // dx1 = xj - xi, dy1 = yj - yi
            // dx2 = xk - xi, dy2 = yk - yi
            int dx1 = points[j][0] - points[i][0], dy1 = points[j][1] - points[i][1];
            int dx2 = points[k][0] - points[i][0], dy2 = points[k][1] - points[i][1];
            if (dy1 * dy2 + dx1 * dx2 != 0) continue;
            
            // center.x = (xj + xk) / 2 = (xi + xm) / 2 => xm = (xj + xk) - xi = (xj - xi) + xk
            // center.y = (yj + yk) / 2 = (yi + ym) / 2 => ym = (yj + yk) - yi = (yj - yi) + yk
            int xm = dx1 + points[k][0], ym = dy1 + points[k][1];
            if (!map.containsKey(xm) || !map.get(xm).contains(ym)) continue;
            
            minArea = Math.min(minArea, Math.sqrt(dx1 * dx1 + dy1 * dy1) * Math.sqrt(dx2 * dx2 + dy2 * dy2));
        }
        return minArea;
    }
    
    public double minAreaFreeRect(int[][] points) {
        int len = points.length;
        // corner case
        if (len < 4) {
            return 0;
        }
        
        // initialize the mapping
        Map<Integer, Set<Integer>> map = new HashMap<>();   // x value->all y valuess
        for (int i = 0; i < len; i++) {
            map.putIfAbsent(points[i][0], new HashSet<>());
            map.get(points[i][0]).add(points[i][1]);
        }
        
        // given 3 points, find the last point
        double minArea = Double.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (j == i) continue;
                
                minArea = Math.min(minArea, findMin(points, i, j, map));
            }
        }
        
        return minArea == Double.MAX_VALUE ? 0 : minArea;
    }
}