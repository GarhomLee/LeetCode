https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/

idea: Prefix Sum + Greedy
    -For each index, the deletions to make string balanced are to delete
     the Bs before it and As after it.
    -Find the index where it has the minimum prefix B and suffix A sums.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] aSum = new int[n+1], bSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            aSum[i] = aSum[i-1] + (s.charAt(i-1) == 'a' ?  1 : 0);
            bSum[i] = bSum[i-1] + (s.charAt(i-1) == 'b' ?  1 : 0);
        }
        
        int res = n;
        for (int i = 1; i <= n; i++) {
            int leftB = bSum[i-1], rightA = aSum[n] - aSum[i];
            res = Math.min(res, leftB + rightA);
        }
        
        return res;
    }
}