https://leetcode.com/problems/queens-that-can-attack-the-king/

// 思路：DFS (Recursion)，将从queen可以移动的8个方向转化为king可以在8个方向被攻击。
//         step0:利用二维board数组，记录所有queen出现的位置为1。
//         step1:从king出发，沿着8个方向搜索最近的queen。只要确定了某一方向，dfs时只需沿着同一个方向即可。
// 时间复杂度：O(8 * max(rowLen, colLen))=O(max(rowLen, colLen))
// 空间复杂度：O(max(rowLen, colLen))

class Solution {
    int[] dir_row = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dir_col = new int[]{0, 0, -1, 1, -1, 1, -1, 1};
    List<List<Integer>> res = new ArrayList<>();
    final int ROW_LEN = 8;
    final int COL_LEN = 8;
    
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] board = new int[ROW_LEN][COL_LEN];
        for (int[] pos : queens) {
            int row = pos[0], col = pos[1];
            board[row][col] = 1;
        }
        
        for (int i = 0; i < dir_row.length; i++) {
            dfs(board, king[0], king[1], i);
        }
        
        return res;
    }
    
    private void dfs(int[][] board, int row, int col, int index) {
        if (row < 0 || row >= ROW_LEN || col < 0 || col >= COL_LEN) {
            return;
        }
        if (board[row][col] == 1) {
            res.add(Arrays.asList(row, col));
            return;
        }
        
        dfs(board, row + dir_row[index], col + dir_col[index], index);
    }
}