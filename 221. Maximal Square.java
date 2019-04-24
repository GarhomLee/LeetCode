https://leetcode.com/problems/maximal-square/

// 解法一：通用解法
// 1）预计算从左上角(0, 0)到所有位置形成的矩形的和。维护sums矩阵，将长和宽都设为比matrix的长和宽多一位是为了简化代码。
// 2）然后遍历数组，以当前元素为左上角顶点，求取能和它形成正方形的右下角顶点所包围的区域的和，看是否都为1。
// 3）为了简化计算，可以从最大的正方形开始求取，逐渐缩小正方形区域。
// 4）时间复杂度：O(m * n * min(m, n)) = O(N^3)；空间复杂度O(m * n)

class Solution {
    public int maximalSquare(char[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int[][] sums = new int[rowLen + 1][colLen + 1];
        /* precompute */
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sums[row][col] = (matrix[row - 1][col - 1] - '0') + sums[row][col - 1] + sums[row - 1][col] - sums[row - 1][col - 1];
            }
        }
        
        int maxSum = 0;
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                for (int size = Math.min(rowLen - row, colLen - col); size >= 0; size--) {
                    int currSum = sums[row + size][col + size] - sums[row + size][col - 1] - sums[row - 1][col + size] + sums[row - 1][col - 1];
                    if (currSum == (size + 1) * (size + 1)) {
                        maxSum = Math.max(maxSum, currSum);
                        break;
                    }
                }
            }
        }
        return maxSum;
    }
}

// 解法二：由于题目的特殊性，可以用Dynamic Programming。可以发现某一元素为1时，能形成的最大正方形只和同列的上一行元素、同行的前一列元素、上一行的前一列元素有关。
// 详细解释：https://www.youtube.com/watch?v=vkFUB--OYy0
// 1）维护dp数组，dp[row][col]表示从左上角(0, 0)到matrix[row - 1][col - 1]所能形成的【包含当前元素matrix[row - 1][col - 1]在内】的最大正方形面积。
// 2）matrix[row - 1][col - 1] == '1'时，能形成的正方形必定从matrix[row - 2][col - 1]，matrix[row - 1][col - 2]和matrix[row - 2][col - 2]扩展而来，所以
//     取dp[row - 1][col], dp[row - 1][col - 1]和dp[row][col - 1]的较小值再+1即为所能形成的【包含当前元素matrix[row - 1][col - 1]在内】的最大正方形面积。
// 3）时间复杂度：O(m * n) = O(N^2)；空间复杂度O(m * n)

class Solution {
    public int maximalSquare(char[][] matrix) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0: matrix[0].length;
        int[][] dp = new int[rowLen + 1][colLen + 1];
        int maxLen = 0;
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                if (matrix[row - 1][col - 1] == '1') {
                    dp[row][col] = Math.min(Math.min(dp[row - 1][col], dp[row - 1][col - 1]), dp[row][col - 1]) + 1;
                    maxLen = Math.max(dp[row][col], maxLen);
                }
            }
        }
        return maxLen * maxLen;
    }
}