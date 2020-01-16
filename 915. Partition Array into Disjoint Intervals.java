https://leetcode.com/problems/partition-array-into-disjoint-intervals/

思路：Greedy，two-pass

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public int partitionDisjoint(int[] A) {
        int len = A.length;
        int maxFromLeft = A[0];
        int[] minFromRight = new int[len];
        minFromRight[len - 1] = A[len - 1];
        
        // from right to left
        for (int i = len - 2; i >= 0; i--) {
            minFromRight[i] = Math.min(minFromRight[i + 1], A[i]);
        }
        
        // from left to right
        for (int i = 1; i < len; i++) {]
            if (maxFromLeft <= minFromRight[i]) {
                // evaluate max(A[0:i-1]) and min(A[i:len-1])
                return i;
            }
            
            maxFromLeft = Math.max(maxFromLeft, A[i]); // update maxFromLeft as max(A[0:i])
        }
        
        return -1;
    }
}