https://leetcode.com/problems/construct-quad-tree/

// 思路：Recursion。要理解题意，boolean isLeaf表示是否当前矩阵范围[startRow:endRow][startCol:endCol]
//         是否为只有0或者只有1的节点，这种节点称为leaf node；boolean val表示当节点为leaf node时节点只有
//         0为false，只有1为true，如果不是leaf node则无所谓。
//         根据题意，可以将当前矩阵范围[startRow:endRow][startCol:endCol]【平均分为4份作为4个children】。
//         首先，遍历一遍当前矩阵范围，确定是否为leaf node，即是否只有0或者只有1。
//         如果是leaf node，那么4个child都为null。
//         如果不是，那么平均分为4个区域后，调用递归函数构建4个child node，然后返回当前node。
// 时间复杂度：O(m*n), m=rowLen, n=colLen
// 空间复杂度：O(log(m*n)), m=rowLen, n=colLen
// 犯错点：1.数据范围错误：调用递归函数时，将当前范围分割一半要用startRow + (endRow - startRow) / 2，
//             如果直接用endRow / 2既不make sense，还会造成越界。


class Solution {
    public Node construct(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        return dfs(0, 0, rowLen - 1, colLen - 1, grid);
    }
    
    private Node dfs(int startRow, int startCol, int endRow, int endCol, int[][] grid) {
        if (startRow > endRow || startCol > endCol) {
            return null;
        }
        
        int val = grid[startRow][startCol];
        boolean isLeaf = true;
        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (grid[row][col] != val) {
                    isLeaf = false;
                    break;
                }
            }
        }
        
        
        if (isLeaf) {
            return new Node(val == 1, isLeaf, null, null, null, null);
        }
        
        /*Node topLeft = dfs(startRow, startCol, endRow / 2, endCol / 2, grid);
        Node topRight = dfs(startRow, endCol / 2 + 1, endRow / 2, endCol, grid);
        Node bottomLeft = dfs(endRow / 2 + 1, startCol, endRow, endCol / 2, grid);
        Node bottomRight = dfs(endRow / 2 + 1, endCol / 2 + 1, endRow, endCol, grid);*/  // {Mistake 1}

        int midRow = startRow + (endRow - startRow) / 2;
        int midCol = startCol + (endCol - startCol) / 2;
        Node topLeft = dfs(startRow, startCol, midRow, midCol, grid);
        Node topRight = dfs(startRow, midCol + 1, midRow, endCol, grid);
        Node bottomLeft = dfs(midRow + 1, startCol, endRow, midCol, grid);
        Node bottomRight = dfs(midRow + 1, midCol + 1, endRow, endCol, grid);  // {Correction 1}

        return new Node(false, isLeaf, topLeft, topRight, bottomLeft, bottomRight);
    }
}



