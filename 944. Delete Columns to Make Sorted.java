https://leetcode.com/problems/delete-columns-to-make-sorted/

idea: Brute force
time complexity: O(m * n)
space complexity: O(1)

class Solution {
    public int minDeletionSize(String[] A) {
        int n = A.length, m = A[0].length();
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[j].charAt(i) < A[j - 1].charAt(i)) {
                    ret++;
                    break;
                }
            }
        }
        
        return ret;
    }
}