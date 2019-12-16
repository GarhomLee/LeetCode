https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/

思路：BFS
写法一：二维boolean数组visited[k][i]表示还剩k个障碍可以移除的在二维坐标的一维位置i是否被搜索过。
时间复杂度：O(rowLen * colLen)
空间复杂度：O(k * rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int shortestPath(int[][] grid, int k) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[k + 1][rowLen * colLen];
        queue.offer(new int[]{0, 0, k});
        visited[k][0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int row = curr[0], col = curr[1], toMove = curr[2];
                if (row == rowLen - 1 && col == colLen - 1) {
                    // target reached
                    return step;
                }
                
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    int nextIndex = nextRow * colLen + nextCol;
                    if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen) continue;
                    
                    if (grid[nextRow][nextCol] == 1) {
                        if (toMove == 0 || visited[toMove - 1][nextIndex]) continue;
                        queue.offer(new int[]{nextRow, nextCol, toMove - 1});
                        visited[toMove - 1][nextIndex] = true;
                    } else {
                        if (visited[toMove][nextIndex]) continue;
                        queue.offer(new int[]{nextRow, nextCol, toMove});
                        visited[toMove][nextIndex] = true;
                    }
                }
            }
            
            step++;
        }
        
        return -1;
    }
}


写法二：参考花花酱，二维int数组obstacle[row][col]表示在位置{row,col}还剩的可以被移除的障碍个数，只能递减。