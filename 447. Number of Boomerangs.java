https://leetcode.com/problems/number-of-boomerangs/

// 题意比较绕，需要花一定时间理解。
// 1）固定某个点，计算其他所有点到这个点的距离，存在Map里，key为距离，value为该距离的点的数量
// 2）如果有n个点到某一点的距离相同，那么组成的三元boomerang的数量利用组合的性质可知有nC1 * (n-1)C1 = n * (n-1)个，不需要特殊考虑n = 1的情况
// 3）遍历过程中，重置Map
// 4）时间复杂度：O(n^2)；空间复杂度：O(n)

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int[] pointA: points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] pointB: points) {
                if (Arrays.equals(pointA, pointB)) continue;
                int distance = (pointA[0] - pointB[0]) * (pointA[0] - pointB[0]) + (pointA[1] - pointB[1]) * (pointA[1] - pointB[1]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            
            for (int count: map.values()) res += count * (count - 1);
        }
        return res;
    }
}