https://leetcode.com/problems/as-far-from-land-as-possible/

// 思路：BFS
//         step1:维护二维数组dist，dist[row][col]表示从[row][col]到最近的1的距离。初始情况下，当[row][col]
//                 为1时dist[row][col]为0，【当[row][col]为0时dist[row][col]为自己设定的最大值MAX】。
//                 遍历grid数组，遇到0时初始化dist[row][col]为MAX，遇到1时放入Queue作为起始seed。
//         step2:当Queue不为空时，进行BFS。
//                 搜索当前位置row][col]的相邻4个方向[nextRow][nextCol]，当且仅当：1）[nextRow][nextCol]
//                 在界内；2）dist[nextRow][nextCol]比dist[row][col]+1大，即搜索到了更近的距离；
//                 只有满足这两个条件，才能将[nextRow][nextCol]放入Queue。
//                 注意：在将[nextRow][nextCol]放入Queue前，【更新dist[nextRow][nextCol]=dist[row][col]+1】。
//         step3: 经过BFS，dist数组的所有值都确定下来，那么遍历dist数组寻找最大值，记为maxDist，初始化为0。
//                 如果遍历结束后，maxDist仍为初始值0，说明grid数组中全是1没有0；或者maxDist为最大值MAX，说明grid数组中
//                 全是0没有1。这两种情况下，返回-1。否则，返回maxDist。
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)
// 犯错点：1.模版不熟：要熟记经典的矩阵遍历BFS模版，找出合适的seed，在加入下一层的元素前先对其进行更新或标记。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    final int MAX = 1_000_000;
    
    public int maxDistance(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int[][] dist = new int[rowLen][colLen];
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 0) {
                    dist[row][col] = MAX;
                } else {
                    queue.offer(new int[]{row, col});
                }
                
            }
        }
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0], currCol = curr[1];
            for (int i = 0; i < 4; i++) {
                int nextRow = currRow + dir[i], nextCol = currCol + dir[i + 1];
                if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length 
                    && dist[nextRow][nextCol] > dist[currRow][currCol] + 1) {
                    dist[nextRow][nextCol] = dist[currRow][currCol] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        
        
        int maxDist = 0;
        for (int[] line: dist) {
            for (int currDist: line) {
                maxDist = Math.max(maxDist, currDist);
            }
        }
        
        return maxDist == 0 || maxDist == MAX ? -1 : maxDist;
    }
}