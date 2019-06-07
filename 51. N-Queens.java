https://leetcode.com/problems/n-queens/

// 总体思路：DFS+Backtracking，关键是要知道棋子放置的规则。
//         goal:遍历完所有行且有合理解，row==n
//         choices:每行有n个放置位置
//         constraints:1）每一行只能放一个棋子
//                     2）每一列只能放一个棋子
//                     3）同一正对角线只能放一个棋子，同一正对角线的棋子有一个规律：【col-row+n-1相同】
//                     4）同一反对角线只能放一个棋子，同一反对角线的棋子有一个规律：【col+row相同】
// 犯错点：1.国际象棋中Queen的走法是会攻击位于同一行、同一列、同一【正对角线和反对角线】的所有棋子，而不是相邻对角线
//        2.边长为n的正方形棋盘有(2*n - 1)条正对角线和(2*n - 1)条反对角线

class Solution {
    List<List<String>> res = new ArrayList();
    //int[] dir = new int[]{1, -1, -1, 1, 1};  // {Mistake 1: this is not how Queen attacks}
    boolean[] colCheck;
    boolean[] diagonal, backDiagonal;
    
    public List<List<String>> solveNQueens(int n) {
        colCheck = new boolean[n];
        //diagonal = new boolean[n];  // {Mistake 2}
        diagonal = new boolean[2 * n - 1];  // {Correction 2: there are (2*n - 1) diagonals as well as back-diagonals instead of n}
        backDiagonal = new boolean[2 * n - 1];
        dfs(n, 0, new ArrayList<String>());
        return res;
    }
    
    private void dfs(int n, int row, List<String> list) {
        /* goal */
        if (row == n) {
            res.add(new ArrayList<String>(list));
            return;
        }
        
        /* initialization of current row */
        StringBuilder sb = new StringBuilder();
        for (int col = 0; col < n; col++) {
            sb.append('.');
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
            sb.setCharAt(col, 'Q');
            list.add(sb.toString());
            /* depth-first search */
            dfs(n, row + 1, list);
            /* reset */
            sb.setCharAt(col, '.');
            list.remove(list.size() - 1);
            colCheck[col] = false;
            diagonal[col - row + n - 1] = false;
            backDiagonal[col + row] = false;
            
        }
    }
    
    private boolean isValid(int n, int row, int col) {
        return !colCheck[col] && !diagonal[col - row + n - 1] && !backDiagonal[col + row];
    }
}