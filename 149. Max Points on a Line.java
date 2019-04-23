https://leetcode.com/problems/max-points-on-a-line/

// 和447. Number of Boomerangs很类似，都需要确定一个点然后遍历其他点，不同的是这道题可以遍历部分的其他点而不用遍历其他所有的点。
// 1）维护变量res，表示最终的返回值，即在同一条线上的点的最大个数
// 2）外层循环：数组里的所有点。维护临时变量samePoints表示重合的点，初始化为1（当前点）；maxSameLine表示除当前点以外的点组成的
//     所有斜率中能得到在同一条线上的点的最大个数，是局部最大值。同时，维护一个Map，key为斜率，用【存在List中的最简分数来表示】，
//     不要除成分数，因为即使浮点数也会有近似；value为斜率出现的次数，用来更新maxSameLine。
// 3）内层循环：【从当前点的下一个点开始】，这是一个优化的步骤。不需要遍历已经遍历过的点，因为和那些点组成的斜率信息都已经被查询
//     过了。如果找到重合的点，samePoints++；否则，求取斜率后更新Map，同时更新maxSameLine。
//     【注意】：水平线和垂直线的表示；最大公约数的求取可以直接利用公式（最好背一下）
// 4）内层循环结束，用maxSameLine + samePoints更新res。

class Solution {
    public int maxPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<List<Integer>, Integer> map = new HashMap<>();
            int samePoints = 1, maxSameLine = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) samePoints++;
                else {
                    List<Integer> slope = slope(points[i], points[j]);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                    maxSameLine = Math.max(maxSameLine, map.get(slope));
                }
            }
            res = Math.max(res, maxSameLine + samePoints);
        }
        return res;
    }
    
    private List<Integer> slope(int[] point1, int[] point2) {
        int dx = point1[0] - point2[0], dy = point1[1] - point2[1];
        if (dx == 0) return Arrays.asList(point1[0], 0);  // vertical line
        if (dy == 0) return Arrays.asList(0, point1[1]);  // horizontal line
        int gcd = gcd(dx, dy);
        return Arrays.asList(dx / gcd, dy / gcd);
    }
    
    private int gcd(int m, int n){
        if (n == 0) return m;
        return gcd(n, m % n);
    }
}