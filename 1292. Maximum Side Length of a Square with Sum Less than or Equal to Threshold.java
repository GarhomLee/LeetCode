https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/

// 思路：Prefix Sum + Binary Search (search in range)
//         在low=0，high=min(rowLen, colLen)的范围内，利用binary search找到这样一个最大边长sideLen，使得存在相应的正方形
//         包含小于等于threshold的元素和。
// 时间复杂度：O(log(min(rowLen, colLen)) * rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)
// 犯错点：1.思路（审题）错误：不是要找边长为sideLen的正方形的最大值是否<=threshold，而是只需要判断是否【存在】边长为
//             sideLen的正方形使得正方形的和<=threshold

class Solution {
    private boolean isLTEQ(int[][] sum, int sideLen, int threshold) {
        int rowLen = sum.length, colLen = rowLen == 0 ? 0 : sum[0].length;
        for (int row = sideLen; row < rowLen; row++) {
            for (int col = sideLen; col < colLen; col++) {
                int currSum = sum[row][col] - sum[row - sideLen][col] - sum[row][col - sideLen] + sum[row - sideLen][col - sideLen];
                if (currSum <= threshold) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public int maxSideLength(int[][] mat, int threshold) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;
        int[][] sum = new int[rowLen + 1][colLen + 1];
        for (int row = 1; row <= rowLen; row++) {
            for (int col = 1; col <= colLen; col++) {
                sum[row][col] = mat[row - 1][col - 1] + (sum[row - 1][col] + sum[row][col - 1] - sum[row - 1][col - 1]);
            }
        }
        
        /* binary search */
        int low = 0, high = Math.min(rowLen, colLen);
        int maxSum = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (!isLTEQ(sum, mid, threshold)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low - 1;
    }
}