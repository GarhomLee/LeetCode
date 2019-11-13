https://leetcode.com/problems/number-of-distinct-islands/

思路：DFS
        递归函数定义：
        终止条件：
        递归过程：
时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)
犯错点：1.细节错误：遍历完当前4个相邻格子后，需要append一个特殊字符来表示当前位置的搜索已经结束。否则，可能
            造成将不同的island当成同一个island。
            eg. [[0,1,1,1]
                 [0,0,1,0]
                 [1,1,0,0]
                 [0,1,1,0]]

class Solution {
    Set<String> set = new HashSet<>();
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int numDistinctIslands(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, row, col, sb, 0);
                    set.add(sb.toString());
                }
            }
        }
        
        return set.size();
    }
    
    private void dfs(int[][] grid, int row, int col, StringBuilder sb, int currDir) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen || grid[row][col] == 0) {
            return;
        }
        
        sb.append(currDir);
        grid[row][col] = 0;
        for (int i =  0; i < 4; i++) {
            int nextRow = row + dir[i], nextCol = col + dir[i + 1];
            dfs(grid, nextRow, nextCol, sb, i);
        }
        // {Mistake 1}
        sb.append('#');  // {Correction 1}
        
    }
}