https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/

idea: Use recursion to reduce to LC 373 "Find K Pairs with Smallest Sums" problem in each level of recursion
time comp: O(rowLen * k log colLen), rowLen = mat.length, colLen = mat[0].length
space comp: O(max(k + colLen))

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int rowLen = mat.length;
        int[] arr = helper(mat, rowLen - 1, k);
        return arr[k - 1];
    }
    
    private int[] helper(int[][] mat, int row, int k) {
        int arrLen = ((int) Math.min(k, Math.pow(mat[0].length, row + 1)));
        int[] arr = new int[arrLen];
        
        if (row == 0) { // base case
            System.arraycopy(mat[row], 0, arr, 0, arrLen);
        } else {    // each level is a LC 373 "Find K Pairs with Smallest Sums" problem
            int[] lastRow = helper(mat, row - 1, k);
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));   // {num1, num2, idx1}
            for (int num: mat[row]) {
                pq.offer(new int[]{lastRow[0], num, 0});
            }
            
            for (int i = 0; i < arrLen; i++) {
                int[] curr = pq.poll();
                arr[i] = curr[0] + curr[1];
                
                int nextIdx = curr[2] + 1;
                if (nextIdx < lastRow.length) {
                    pq.offer(new int[]{lastRow[nextIdx], curr[1], nextIdx});
                }
            }
        }
        
        return arr;
    }
}