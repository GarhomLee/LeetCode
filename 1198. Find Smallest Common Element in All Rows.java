https://leetcode.com/problems/find-smallest-common-element-in-all-rows/

// 解法一：Brute Force
//         统计每个元素出现的总次数，看第0行mat[0]中元素是否有出现行数rowLen次。
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)


// 解法二：Binary Search
//         对于每个mat[0]的元素，利用Binary Search搜索是否在mat[1:rowLen)也出现过，如果出现了那么count++，
//         否则跳出循环。
//         因为已经排好序，所以返回第一个count为rowLen的mat[0][c]。如果不存在任何一个count为rowLen，那么返回-1。
// 时间复杂度：O(m log n * n)
// 空间复杂度：O(1)

class Solution {
    public int smallestCommonElement(int[][] mat) {
        int rowLen = mat.length, colLen = rowLen == 0 ? 0 : mat[0].length;
        for (int c = 0; c < colLen; c++) {
            boolean isInvalid = false;
            int count = 1;
            for (int r = 1; r < rowLen && !isInvalid; r++) {
                int index = Arrays.binarySearch(mat[r], mat[0][c]);
                if (index < 0) {
                    isInvalid = true;
                } else {
                    count++;
                }
            }
            
            if (count == rowLen) {
                return mat[0][c];
            }
        }
        
        return -1;
    }
}