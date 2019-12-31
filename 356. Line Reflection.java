https://leetcode.com/problems/line-reflection/

// 思路：Math + HashSet
//         假设存在与y轴平行的竖线使得所有点都能找到对称点，那么可以从几何性质上发现对称点的y值相同，
//         所有对称点的x值加和都相同。因此，只需要找到最小和最大的x值的加和sum，再对每个点判断是否
//         有另一个点使得两点x值加和为sum。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public boolean isReflected(int[][] points) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
            set.add(point[0] + "," + point[1]);
        }
        
        int sum = max + min;
        for (int[] point : points) {
            String reflection = (sum - point[0]) + "," + point[1];
            if (!set.contains(reflection)) {
                return false;
            }
        }
        
        return true;
    }
}