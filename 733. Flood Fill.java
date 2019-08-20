https://leetcode.com/problems/flood-fill/

// 解法一：DFS模版
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)


// 解法二：BFS模版
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)
// 犯错点：1.初始化错误：在[sr][sc]加入Queue作为初始元素之前，需要先将image[sr][sc]赋值为newColor。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        int rowLen = image.length, colLen = rowLen == 0 ? 0 : image[0].length;
        /* corner case */
        if (oldColor == newColor) {
            return image;
        }
        /* BFS */
        Queue<int[]> queue = new LinkedList<>();
        // {Mistake 1}
        image[sr][sc] = newColor;  // {Correction 1}
        queue.offer(new int[]{sr, sc});  // initialization
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int i = 0; i < dir.length - 1; i++) {  // search 4 adjacent neighbors
                int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];
                if (nextRow >=0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && image[nextRow][nextCol] == oldColor) {
                    image[nextRow][nextCol] = newColor;  // set the next element before adding into Queue
                    queue.offer(new int[]{nextRow, nextCol});  // add the next element into Queue for the future search
                }
            }
        }
        
        return image;
    }
}