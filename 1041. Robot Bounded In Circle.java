https://leetcode.com/problems/robot-bounded-in-circle/

思路：Math，证明？参考：https://leetcode.com/problems/robot-bounded-in-circle/discuss/290856/JavaC%2B%2BPython-Let-Chopper-Help-Explain
        找规律，发现如果instructions结束时面朝方向为北，且没有回到原点，那么一定不会有bounded circle。
        换言之，如果结束时回到了原点，或者面朝方向不为北，最后一定会形成bounded circle。
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // N, W, S, E
    
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, currDir = 0;
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                currDir = (currDir + 1) % dir.length;
            } else if (c == 'R') {
                currDir = (currDir + dir.length - 1) % dir.length;
            } else {
                x += dir[currDir][0];
                y += dir[currDir][1];
            }
        }
        
        return (x == 0 && y == 0) || currDir != 0;
    }
}