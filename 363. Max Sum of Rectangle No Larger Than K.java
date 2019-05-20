https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/

// 总体思路：属于560. Maximum Size Subarray Sum Equals k的follow-up，将一维问题拓展到二维。
//          最外层循环，遍历不同的起始行（或列，取长度较短的，作为优化）；中层循环，遍历不同的终止行（或列，取长度较短的），这样就能和起始行（或列，取长度较短的）
//          组成一个rectangle；最内层循环，遍历当前列（或行，取长度较长的）。
//          对于rectangle扩增时扫描到的当前元素，发展出新的rectangle，把这个rectangle的sums压缩成一维的数组。sums[t]表示同一行（或列）t的从i到j的和，注意sums[t]
//          和其他某一sums[t']是相互独立的，所以需要再维护squareSum来表示当前得到的rectangle的面积。
//          对于这个rectangle的sums压缩成的一维的数组，要找小于k的sum，需要用到range sum的思想。如果sums[0:t]中存在某一sums[0:t']使得sums[0:t'] + k == sums[0:t]，那么
//          直接可以返回k。否则，比sums[0:t']大的最小的那个squareSum，即它的ceiling，可以满足sums[0:t] - ceiling(sums[0:t']) < k的要求，这时候sums[0:t] - ceiling(sums[0:t'])
//          就是要求的maxSum。因此，需要用到【TreeSet，便于在O(log n)时间内找到ceiling】。TreeSet的元素为sums[0:t]，即squareSum。
// 时间复杂度：O(m^2 * n * log n), m = min(rowLen, colLen), n = max(rowLen, colLen)
// 空间复杂度：O(n), n = max(rowLen, colLen)
// 犯错点：1.TreeSet需要加入初始的0，表示在初始阶段square面积为0

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rowLen = matrix.length, colLen = rowLen == 0? 0 : matrix[0].length;
        int m = Math.min(rowLen, colLen), n = Math.max(rowLen, colLen);  // optimization: let the shorter one of row and col be the outer loop
        int maxSum = Integer.MIN_VALUE;  // sum might be negative, so maxSum should be initialized as Integer.MIN_VALUE
        for(int i = 0; i < m; i++) {
            int[] sums = new int[n];  
            for (int j = i; j >= 0; j--) {
                TreeSet<Integer> set = new TreeSet<>();  // store the sum of current square
                set.add(0);  // {Mistake 1: should add 0 first as the sum should be 0 when the square is empty.} {Correction 1}
                int squareSum = 0;  // squareSum is sums[0:t], which is from i to j
                for (int t = 0; t < n; t++) {
                    sums[t] += rowLen < colLen ? matrix[j][t] : matrix[t][j];  // sums[t] indicates the sum of current row (or col depending on their length) from i to j
                    squareSum += sums[t];  // update squareSum according to sums[t]
                    Integer ceiling = set.ceiling(squareSum - k);  // ceiling searching should come before adding squareSum into TreeSet
                    if (squareSum - k == 0) return k;  // optimization: k is the largest possible max sum
                    if (ceiling != null) {  // when k == 0, ceiling might be null
                        maxSum = Math.max(maxSum, squareSum - ceiling);
                    }
                    set.add(squareSum);  // add squareSum into TreeSet afterwards
                }
            }
        }
        return maxSum;
    }
}