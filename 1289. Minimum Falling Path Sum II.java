https://leetcode.com/problems/minimum-falling-path-sum-ii/

对比：931. Minimum Falling Path Sum, 265. Paint House II

思路：DP

时间复杂度：O(n^3)
空间复杂度：O(1)

class Solution {
    public int minFallingPathSum(int[][] arr) {
        int len = arr.length;
        for (int row = 1; row < len; row++) {
            for (int col = 0; col < len; col++) {
                int temp = Integer.MAX_VALUE;;
                for (int i = 0; i < len; i++) {
                    if (i == col) continue;
                    
                    temp = Math.min(temp, arr[row - 1][i]);
                }
                arr[row][col] += temp;
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < len; col++) {
            min = Math.min(min, arr[len - 1][col]);
        }
        
        return min;
    }
}


优化：类似265. Paint House II，利用2个变量时间复杂度优化至O(n^2)。