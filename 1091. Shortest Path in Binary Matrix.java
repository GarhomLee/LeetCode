https://leetcode.com/problems/shortest-path-in-binary-matrix/

思路：BFS，套模版
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)

class Solution {
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if(grid[0][0] != 0 || grid[rowLen - 1][colLen - 1] != 0) {
            return -1;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowLen][colLen];
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                if (curr[0] == rowLen - 1 && curr[1] == colLen - 1) {
                    return step;
                }
                
                for (int i = 0; i < dir.length; i++) {
                    int[] next = new int[]{curr[0] + dir[i][0], curr[1] + dir[i][1]};
                    if (next[0] < 0 || next[0] >= rowLen || next[1] < 0 || next[1] >= colLen || grid[next[0]][next[1]] == 1 || visited[next[0]][next[1]]) continue;
                    
                    queue.offer(next);
                    visited[next[0]][next[1]] = true;
                }
            }
            
            step++;
        }
        return -1;
    }
}