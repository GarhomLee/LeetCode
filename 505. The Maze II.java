https://leetcode.com/problems/the-maze-ii/

思路：BFS
时间复杂度：O(rowLen * colLen * max(rowLen, colLen))
空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int rowLen = maze.length, colLen = rowLen == 0 ? 0 : maze[0].length;
        int[][] steps = new int[rowLen][colLen];
        // initialize as -1
        for (int row = 0; row < rowLen; row++) {
            Arrays.fill(steps[row], -1);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        steps[start[0]][start[1]] = 0;  // initialize the step at start cell
        while (!queue.isEmpty()) {
            int[] currPoint = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = currPoint[0] + dir[i], nextCol = currPoint[1] + dir[i + 1];
                int currStep = currPoint[2];  // get the step from the last search

                // roll all the way to the wall
                while (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen
                     && maze[nextRow][nextCol] == 0) {
                    currStep++;
                    nextRow += dir[i];
                    nextCol += dir[i + 1];
                }
                
                nextRow -= dir[i];
                nextCol -= dir[i + 1];
                
                // enqueue this cell only if it is not visited yet, or the current step is
                // smaller than its previous step
                if (steps[nextRow][nextCol] == -1 || currStep < steps[nextRow][nextCol]) {
                    steps[nextRow][nextCol] = currStep;
                    queue.offer(new int[]{nextRow, nextCol, currStep});
                }
            }
            
        }
        
        return steps[destination[0]][destination[1]];
    }
}