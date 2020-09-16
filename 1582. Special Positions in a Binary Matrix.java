https://leetcode.com/problems/special-positions-in-a-binary-matrix/

idea: Info Cache, 2-pass
time complexity: O(n * m)
space complexity: O(n + m)

class Solution {
    public int numSpecial(int[][] mat) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;
        int[] rowCount = new int[rowLen], colCount = new int[colLen];
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                rowCount[row] += mat[row][col];
                colCount[col] += mat[row][col];
            }
        }
        
        int res = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                res += rowCount[row] == 1 && colCount[col] == 1 && mat[row][col] == 1 ? 1 : 0;
            }
        }
        
        return res;
    }
}