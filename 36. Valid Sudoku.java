https://leetcode.com/problems/valid-sudoku/

// 解法一：利用数组模拟HashTable的mapping
// 1）维护3个二维数组，分别记录行、列、块中数字出现的情况。第一个维度为当前行/列/块，第二个维度为数字对应的字符'1'到'9'，数组的value为出现的次数
// 2）遍历board里的所有元素，如果不是'.'，那么需要判断当前元素是不是在当前行、列、块都是唯一出现（即数组中value为0）。如果不是，那么出现重复，返回false
// 3）块的编号求取可以单独写成一个小的function，利用数学性质

class Solution {
    public boolean isValidSudoku(char[][] board) {
        //int rowLen = board.length, colLen = rowLen == 0? 0 : board[0].length;
        int size = 9;
        int[][] rowCheck = new int[size][128], colCheck = new int[size][128], blockCheck = new int[size][128];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char c = board[row][col];
                if (c == '.') continue;
                int block = getBlock(size, row, col);
                if (rowCheck[row][c] > 0 || colCheck[col][c] > 0 || blockCheck[block][c] > 0) return false;
                rowCheck[row][c]++;
                colCheck[col][c]++;
                blockCheck[block][c]++;
            }
        }
        return true;
    }
    private int getBlock(int size, int row, int col) {
        return row / 3 * 3 + col / 3;
    }
}

// 解法二：利用Set中元素的唯一性

class Solution {
    public boolean isValidSudoku(char[][] board) {
     Set strSet = new HashSet();
     for (int i=0; i<9; ++i) {
         for (int j=0; j<9; ++j) {
             char number = board[i][j];
             if (number != '.')
                 if (!strSet.add(number + "row" + i) ||
                     !strSet.add(number + "col" + j) ||
                     !strSet.add(number + "block" + i/3 + "-" + j/3))
                     return false;
         }
     }
     return true;
  }
 }