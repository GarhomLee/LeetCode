https://leetcode.com/problems/search-a-2d-matrix-ii/

// 总体思路：不是常规的二分法思路，不能简单套用二分法模版。利用的是二分法的本质思想，即每次搜索逐渐缩小搜索空间。
//         从最右上角matrix[0][colLen-1]开始：
//         （1）如果找到target，直接返回true
//         （2）如果target比当前元素matrix[row][col]大，那么跟matrix[row][col]同一行的左边的元素不必再搜索，搜索空间缩小为下部分matrix[row+1:rowLen-1][0:col]，即row++
//         （3）如果target比当前元素matrix[row][col]小，那么跟matrix[row][col]同一列的下边的元素不必再搜索，搜索空间缩小为左部分matrix[row:rowLen-1][0:col-1]，即col--
// 时间复杂度：O(m+n), m=matrix.length, n=matrix[0].length
// 空间复杂度：O(1)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        if (rowLen == 0 || colLen == 0) return false;
        
        int row = 0, col = colLen - 1;
        while (row < rowLen && col >= 0) {
            if (target == matrix[row][col]) return true;
            else if (target > matrix[row][col]) row++;
            else col--;
        }
        return false;
    }
}