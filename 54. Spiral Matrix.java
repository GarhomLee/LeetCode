// https://leetcode.com/problems/spiral-matrix/

// 1）维护4个（2对）变量，分别表示首行/列和尾行/列。
// 2）按右、下、左、上的顺序遍历matrix并加入list。遍历的方法可分为两种，有细微差别。
// （a）在每个方向，先判断起止行/列是否valid，然后遍历。这意味着每次循环都要经过4次判断
// （b）用switch case，每次while loop只执行其中一个case


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int startRow = 0, endRow = rowLen - 1, startCol = 0, endCol = colLen - 1;
        
        while (startRow <= endRow && startCol <= endCol) {
            if (startRow <= endRow) {
                for (int col = startCol; col <= endCol; col++) list.add(matrix[startRow][col]);
                startRow++;
            }
            
            if (startCol <= endCol) {
                for (int row = startRow; row <= endRow; row++) list.add(matrix[row][endCol]);
                endCol--;
            }
            
            if (startRow <= endRow) {
                for (int col = endCol; col >= startCol; col--) list.add(matrix[endRow][col]);
                endRow--;
            }
            
            if (startCol <= endCol) {
                for (int row = endRow; row >= startRow; row--) list.add(matrix[row][startCol]);
                startCol++;  
            }
        }
        return list;
    }
}