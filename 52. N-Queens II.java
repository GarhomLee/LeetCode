https://leetcode.com/problems/n-queens-ii/

// 总体思路：51. N-Queens的follow-up，求解的总个数。
//         DFS+Backtracking，只需要将51. N-Queens的代码改一点点，删掉具体解的存储，只需要在遍历完得到合理解后计数器res++。
//         goal:遍历完所有行且有合理解，row==n
//         choices:每行有n个放置位置
//         constraints:1）每一行只能放一个棋子
//                     2）每一列只能放一个棋子
//                     3）同一正对角线只能放一个棋子，同一正对角线的棋子有一个规律：【col-row+n-1相同】
//                     4）同一反对角线只能放一个棋子，同一反对角线的棋子有一个规律：【col+row相同】

class Solution {
    int res = 0;
    boolean[] colCheck;
    boolean[] diagonal, backDiagonal;
    
    public int totalNQueens(int n) {
        colCheck = new boolean[n];
        diagonal = new boolean[2 * n - 1]; 
        backDiagonal = new boolean[2 * n - 1];
        dfs(n, 0);
        return res;
    }
    
    private void dfs(int n, int row) {
        /* goal */
        if (row == n) {
            res++;
            return;
        }

        for (int col = 0; col < n; col++) {  // choices
            /* constraints: check validity */
            if (!isValid(n, row, col)) {  // not valid
                continue;
            }

            /* state change */
            colCheck[col] = true;
            diagonal[col - row + n - 1] = true;
            backDiagonal[col + row] = true;
            
            /* depth-first search */
            dfs(n, row + 1);
            /* reset */
            colCheck[col] = false;
            diagonal[col - row + n - 1] = false;
            backDiagonal[col + row] = false;
        }
    }
    
    private boolean isValid(int n, int row, int col) {
        return !colCheck[col] && !diagonal[col - row + n - 1] && !backDiagonal[col + row];
    }
}