https://leetcode.com/problems/minimum-area-rectangle/

思路：HashMap
时间复杂度：O(n^2)
空间复杂度：O(n)

class Solution {
    public int minAreaRect(int[][] points) {
        int rowLen = points.length, colLen = points[0].length;
        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        for (int[] point: points) {
            rowMap.putIfAbsent(point[0], new HashSet<>());
            rowMap.get(point[0]).add(point[1]);
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < rowLen; i++) {
            for (int j = i + 1; j < rowLen; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 || y1 == y2) continue;
                
                if (rowMap.get(x1).contains(y2) && rowMap.get(x2).contains(y1)) {
                    min = Math.min(min, Math.abs(y2 - y1) * Math.abs(x2 - x1));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}