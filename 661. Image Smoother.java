https://leetcode.com/problems/image-smoother/

// 思路：Bit Manipulation，利用bit进行状态压缩。类似289. Game of Life。
//         step1:对每一个元素M[row][col]，遍历以自己为中心的9个有效元素（即不越界），求和存到变量sum，并统计有效元素个数存到count。
//                 将平均值sum / count存到M[row][col]的前面的bit里。
//         step2:再次遍历每一个元素M[row][col]，将bit右移8位，即为所求的结果。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：由于将未来状态储存在前面的bits里，因此不能直接用M[nextRow][nextCol]求和，而需要取后面的8个bits
//             代表的数进行求和，即M[nextRow][nextCol] & 255

class Solution {
    public int[][] imageSmoother(int[][] M) {
        int rowLen = M.length, colLen = rowLen == 0 ? 0 : M[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                int sum = 0, count = 0;
                
                for (int nextRow = row - 1; nextRow <= row + 1; nextRow++) {
                    for (int nextCol = col - 1; nextCol <= col + 1; nextCol++) {
                        if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen) continue;
                        //sum += (M[nextRow][nextCol]);  // {Mistake 1}
                        sum += (M[nextRow][nextCol] & 255);  // {Correction 1}
                        count++;
                    }
                }
                
                M[row][col] |= ((sum / count) << 8);
            }
        }
        
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                M[row][col] = (M[row][col] >> 8);
            }
        }
        
        return M;
    }
}