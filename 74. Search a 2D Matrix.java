https://leetcode.com/problems/search-a-2d-matrix/

// 解法一：分行列分别进行Binary Search
// 时间复杂度：O(log(m)+log(n)), m=matrix.length, n=matrix[0].length
// 空间复杂度：O(1)
// 犯错点：都是关于边界条件。
//         1.当colLen==0时，按行搜索时colLen - 1会超边界，所以要提前进行判断
//         2.按行搜索结束后，如果target比所有元素都大，low指针会超边界，所以也要在进入列搜索前进行判断

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
        if (rowLen == 0 || colLen == 0) return false;  // {Mistake 1}
                                                       // {Correction 1}
        
        int lowRow = 0, highRow = rowLen - 1;
        while (lowRow <= highRow) {
            int midRow = lowRow + (highRow - lowRow) / 2;
            if (matrix[midRow][colLen - 1] >= target) highRow = midRow - 1;
            else lowRow = midRow + 1;
        }
        
        if (lowRow >= rowLen) return false;  // {Mistake 2}
                                             // {Correction 2}
        
        
        int lowCol = 0, highCol = colLen - 1;
        while (lowCol <= highCol) {
            int midCol = lowCol + (highCol - lowCol) / 2;
            if (matrix[lowRow][midCol] >= target) highCol = midCol - 1;
            else lowCol = midCol + 1;
        }
        return matrix[lowRow][lowCol] == target;
    }
}

// 解法二：由于matrix设置的特殊性，将整个二维matrix当成一维数组进行Binary Search
// 时间复杂度：O(log(m*n)), m=matrix.length, n=matrix[0].length
// 空间复杂度：O(1)
// 注意：边界条件，如行或列为空，或最后low超边界

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        
        int low = 0, high = rowLen * colLen - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid / colLen][mid % colLen] >= target) high = mid - 1;
            else low = mid + 1;
        }
        return low / colLen < rowLen && matrix[low / colLen][low % colLen] == target;
    }
}