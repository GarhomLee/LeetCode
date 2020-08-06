https://leetcode.com/problems/shortest-distance-from-all-buildings/

idea: BFS
time complexity: O((m*n)^2)
space complexity: O(m*n)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    /** Return true if all other buildings are reachable from this building */
    private boolean bfs(int[][] grid, int row, int col, int[][] dist, int[][] reachCount, int buildingCount) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        boolean[][] visited = new boolean[rowLen][colLen];
        visited[row][col] = true;
        
        int count = 1, step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];
                    if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen || visited[nextRow][nextCol]) continue;

                    visited[nextRow][nextCol] = true;
                    dist[nextRow][nextCol] += step + 1; // update dist
                    if (grid[nextRow][nextCol] == 0) {
                        queue.offer(new int[]{nextRow, nextCol});
                        reachCount[nextRow][nextCol]++; // update reachCount
                    } else if (grid[nextRow][nextCol] == 1) {
                        count++;
                    }
                }
            }
            step++;
        }
        
        return count == buildingCount;  // if some buildings are not reachable from this building, count < buildingCount
    }
    
    public int shortestDistance(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int buildingCount = 0;
        int[][] dist = new int[rowLen][colLen]; // dist[i][j] means total distance from [i][j] to all 1s
        int[][] reachCount = new int[rowLen][colLen];   // reachCount[i][j] means total reachable building num from [i][j]
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                buildingCount += grid[row][col] == 1 ? 1 : 0;
            }
        }
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    if (!bfs(grid, row, col, dist, reachCount, buildingCount)) {
                        return -1;
                    }
                }
            }
        }
        
        int minDist = Integer.MAX_VALUE;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 0 && reachCount[row][col] == buildingCount) {
                    minDist = Math.min(minDist, dist[row][col]);
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist; 
    }
}