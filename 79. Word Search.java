https://leetcode.com/problems/word-search/

总体思路：DFS+Backtracking
        goal: index == word.length()
        choices:相邻四个元素
        constraints:1）元素在board范围内，不过界
                    2）没有被搜索过，不带'*'标记
                    3）board[row][col]和word.charAt(index)相等，表示match了
犯错点：1.先进行index==word.length()？的判断，保证接下来word.charAt(index)可用。
       2.要记录当前正在搜索的元素，用'*'做标记，如果碰到带'*'标记的元素直接返回false。搜索完成要reset回去。
       3.搜索相邻四个元素时，row + dir[i], col + dir[i + 1]
class Solution {
    int[] dir = new int[]{0, 1, 0, -1, 0};
    
    public boolean exist(char[][] board, String word) {
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (board[row][col] == word.charAt(0) && dfs(board, row, col, word, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, String word, int index) {
        /* corner cases, goal */
        if (index == word.length()) return true;  // {Mistake 1} {Correction 1: index should be evaluated at the very beginning so that word.charAt(index) is valid}
        
        /* constraints */
        //if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index)) return false;  // {Mistake 2}
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '*' || board[row][col] != word.charAt(index)) return false;  // {Correction 2: visited board element should be recorded so that dfs will not search back the same element it comes from}
                
        char c = board[row][col]; 
        board[row][col] = '*';  // {Correction 2: mark as visited}
        
        /* depth-first search, choices */
        for (int i = 0; i < 4; i++) {
            //if (dfs(board, row + i, col + i, word, index + 1)) return true;  // {Mistake 3}
            if (dfs(board, row + dir[i], col + dir[i + 1], word, index + 1)) return true;  // {Correction 3: row should add the i-th element of dir array and col should add the (i+1)-th element}
        }
        
        board[row][col] = c;  // reset
        return false;
    }
}