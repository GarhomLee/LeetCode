https://leetcode.com/problems/diagonal-traverse/

// 思路：matrix扫描。视频讲解：https://www.youtube.com/watch?v=uj65eeIScnQ
//         关键点在于将扫描分为两种情况：从左下到右上up right，和从右上到左下down left。
//         可以发现规律，总的扫描次数为rowLen + colLen - 1，从第0次开始，【偶数次的扫描方向为up right，
//         奇数次的扫描方向为down left】。
//         如果当前扫描次数i为偶数，那么判断起始位置[startRow][startCol]，看i是否在rowLen范围内。然后，
//         从[startRow][startCol]开始，依次将元素填入res数组，从左下到右上（row--，col++），直至到达边界。
//         如果当前扫描次数i为奇数，判断起始位置[startRow][startCol]时看i是否在colLen范围内。然后，
//         从[startRow][startCol]开始，依次将元素填入res数组，从右上到左下（row++，col--），直至到达边界。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)
// 犯错点：1.循环statement 2错误：由于给定的矩阵不一定为N*N，因此不能通过判断是否row和col交换来终止，只能通过
//             判断是否越界来终止。
//         2.循环statement 3错误：如果row和col的更新放在statement 3来执行，那么在循环当中就不需要再用
//             matrix[row--][col++]或者matrix[row++][col--]来更新。

class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        int[] res = new int[rowLen * colLen];
        int index = 0;
        int scanCount = rowLen + colLen - 1;  // total number of scan going up right and down left
        for (int i = 0; i < scanCount; i++) {
            if ((i & 1) == 0) {  // even number of scan, go up right
                int startRow = i < rowLen ? i : rowLen - 1;
                int startCol = i < rowLen ? 0 : i - rowLen + 1;
                //for (int row = startRow, col = startCol; row >= startCol; row--, col++)  // {Mistake 1}
                for (int row = startRow, col = startCol; row >= 0 && col < colLen; row--, col++) {  // {Correction 1}
                    //res[index++] = matrix[row--][col++];  // {Mistake 2}
                    res[index++] = matrix[row][col];  // {Correction 2}
                }
            } else {  // odd number of scan, go down left
                int startRow = i < colLen ? 0 : i - colLen + 1;
                int startCol = i < colLen ? i : colLen - 1;
                //for (int row = startRow, col = startCol; row <= startCol; row++, col--)  // {Mistake 1}
                for (int row = startRow, col = startCol; row < rowLen && col >= 0; row++, col--) {  // {Correction 1}
                    //res[index++] = matrix[row++][col--];  // {Mistake 2}
                    res[index++] = matrix[row][col];  // {Correction 2}
                }
            }
        }
        
        return res;
    }
}