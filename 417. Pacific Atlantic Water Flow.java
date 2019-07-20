https://leetcode.com/problems/pacific-atlantic-water-flow/

// 思路：DFS (Recursion) + Memoization。视频讲解https://www.youtube.com/watch?v=zV3o4XVoU8M
//         逆向思维：与其找从高地向低到边缘“海洋”的路径，不如反过来找从“海洋”向高地找“水的源头”。这种方法【不需要回溯】。
//         因为从两个方向出发（Pacific和Atlantic），因此要【从两组边缘出发，进行两次DFS搜索，取结果的交集】。
//         维护boolean二维数组visitedPacific表示从Pacific（即左边缘和上边缘）出发经过的路径，同理
//         visitedAtlantic表示从Atlantic（即下边缘和右边缘）出发经过的路径。
//         递归函数定义：dfs(int[][] matrix, int row, int col, int height, boolean[][] visited)，
//                 表示搜索matrix[row][col]是否是从某个方向过来的合适路径，height表示来源的邻居的高度。
//         终止条件：1）超出matrix范围
//                 2）当前高度matrix[row][col]比来源的邻居的高度height小。根据题意，“水的源头”应该是相同高度或更高
//         递归过程：当前matrix[row][col]是合理的路径，因此visited[row][col]标记为true。然后递归搜索相邻四个方向即可。
// 注意：用一个dfs函数即可，只需要改变参数row，col，和visited的传递即可

class Solution {
    int[] dir = new int[]{0, 1, 0, -1, 0};
    boolean[][] visitedPacific;
    boolean[][] visitedAtlantic;
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        List<List<Integer>> res = new ArrayList();
        visitedPacific = new boolean[rowLen][colLen];
        visitedAtlantic = new boolean[rowLen][colLen];
        
        for (int row = 0; row < rowLen; row++) {
            dfs(matrix, row, 0, -1, visitedPacific);
            dfs(matrix, row, colLen - 1, -1, visitedAtlantic);
        }
        for (int col = 0; col < colLen; col++) {
            dfs(matrix, 0, col, -1, visitedPacific);
            dfs(matrix, rowLen - 1, col, -1, visitedAtlantic);
        }
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (visitedPacific[row][col] && visitedAtlantic[row][col]) {
                    res.add(Arrays.asList(row, col));
                }
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] matrix, int row, int col, int height, boolean[][] visited) {
        /* termination conditions */
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || visited[row][col] || matrix[row][col] < height) return;
        
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            dfs(matrix, row + dir[i], col + dir[i + 1], matrix[row][col], visited);
        }
    }
}


其他解法：BFS；DP