https://leetcode.com/problems/reshape-the-matrix/

// 思路：二维数组在内存中的表示实际上是一维的，所以只需要遍历原矩阵nums，将每个元素放进新矩阵matrix，同时更新指示matrix
//      列的指针newCol。如果填满了matrix的一列，newCol会变为0，那么更新行指针newRow。
// 犯错点：1.题目理解错误：r和c才是新的矩阵的长和宽，而rowLen和colLen来自原来变形前的矩阵

class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rowLen = nums.length, colLen = rowLen == 0 ? 0 : nums[0].length;
        /* corner case */
        if (rowLen * colLen != r * c) return nums;
        
        //int[][] matrix = new int[rowLen][colLen];  // {Mistake 1}
        int[][] matrix = new int[r][c];  // {Correction 1}
        int newRow = 0, newCol = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                matrix[newRow][newCol] = nums[row][col];
                newCol = (newCol + 1) % c;
                newRow += newCol == 0 ? 1 : 0;  // if newCol == 0, it reaches a new row, thus newRow += 1
            }
        }
        return matrix;
    }
}