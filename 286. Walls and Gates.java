https://leetcode.com/problems/walls-and-gates/

// 解法一：BFS，套模版。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public void wallsAndGates(int[][] rooms) {
        int rowLen = rooms.length, colLen = rowLen == 0 ? 0 : rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (rooms[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                }
            }
        }
        
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];
                    if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen
                        || rooms[nextRow][nextCol] == -1 || rooms[nextRow][nextCol] <= dist + 1) {
                        continue;
                    }
                    
                    rooms[nextRow][nextCol] = dist + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
                
            }
            
            dist++;
        }
    }
}


解法二：DFS