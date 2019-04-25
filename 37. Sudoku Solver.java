https://leetcode.com/problems/sudoku-solver/

// 36. Valid Sudoku的follow-up，Hash Table + BackTracking的题，比较难。
// 1）维护3个二维数组，分别记录行、列、块中数字出现的情况。第一个维度为当前行/列/块，第二个维度为数字对应的字符'1'到'9'，数组的value为
//     是否出现（0为没出现，1为已出现）。先遍历一遍board进行预先记录。
// 2）利用helper method来解数独，【需要返回boolean】。
// 3）用helper method实现Backtracking，从左往右、从上到下对空位进行填充。需要明确：
//     （a）goal：row == 9，说明整个board填充完成，返回true
//     （b）constraints：当前位置的rowCheck，colCheck，blockCheck都不能为1，即不能出现重复
//     （c）choices：可填充的数字为1到9
// 4）先找到下一个需要用recursion搜寻的位置nextCol和nextRow。如果当前元素为'.'，直接返回从下一个位置开始搜索的结果。
// 5）改变当前rowCheck，colCheck，blockCheck和board的元素的状态，然后从下一个位置开始搜索。如果为true，说明找到可行解，所以返回true
// 6）如果从下一个位置开始搜索失败，重置rowCheck，colCheck，blockCheck和board的元素的状态，这一步是【Backtracking的常规步骤】。
// 7）9个数字都无法成功填充，跳出循环，返回false

class Solution {
    int size = 9;
    int[][] rowCheck = new int[size][128], colCheck = new int[size][128], blockCheck = new int[size][128];
    public void solveSudoku(char[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char c = board[row][col];
                if (c == '.') continue;
                rowCheck[row][c] = 1;
                colCheck[col][c] = 1;
                blockCheck[getBlock(row, col)][c] = 1;
            }
        }
        solve(board, 0, 0);
    }
    private boolean solve(char[][] board, int row, int col) {
        if (row == 9) return true;
        int nextCol = (col + 1) % 9, nextRow = nextCol == 0? row + 1 : row;
        if (board[row][col] != '.') return solve(board, nextRow, nextCol);
        
        for (int i = 1; i <= 9; i++) {
            if (rowCheck[row][i + '0'] == 0 && colCheck[col][i + '0'] == 0 && blockCheck[getBlock(row, col)][i + '0'] == 0) {
                rowCheck[row][i + '0'] = 1;
                colCheck[col][i + '0'] = 1;
                blockCheck[getBlock(row, col)][i + '0'] = 1;
                board[row][col] = (char) (i + '0');
                if (solve(board, nextRow, nextCol)) return true;
                rowCheck[row][i + '0'] = 0;
                colCheck[col][i + '0'] = 0;
                blockCheck[getBlock(row, col)][i + '0'] = 0;
                board[row][col] = '.';
            }
        }
        return false;
    }
    private int getBlock(int row, int col) {
        return (row / 3) * 3 + col / 3;
    }
}