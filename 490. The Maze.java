https://leetcode.com/problems/the-maze/

解法一：BFS
        将每走一步都把格子加入Queue改成每次在同一方向走到底才将格子加入Queue。
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rowLen = maze.length, colLen = rowLen == 0 ? 0 : maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowLen][colLen];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];                 
                while (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && maze[nextRow][nextCol] == 0) {
                    nextRow += dir[i];
                    nextCol += dir[i + 1];
                }
                nextRow -= dir[i];
                nextCol -= dir[i + 1];
                if (visited[nextRow][nextCol]) continue;
                
                if (nextRow == destination[0] && nextCol == destination[1]) {
                    return true;
                }
                queue.offer(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
        
        return false;
    }
}


解法一：DFS
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)