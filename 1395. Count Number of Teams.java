https://leetcode.com/problems/count-number-of-teams/

idea: DP
    -For each element, count the ones less than or greater than it to the left or right with brute force.
time complexity: O(n^2)
space complexity: O(1)

class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length, res = 0;
        for (int i = 0; i < n; i++) {
            int leftGreater = 0, leftSmaller = 0, rightGreater = 0, rightSmaller = 0;
            for (int j = 0; j < n; j++) {
                if (i < j) {
                    leftSmaller += rating[j] < rating[i] ? 1 : 0;
                    leftGreater += rating[j] > rating[i] ? 1 : 0;
                } else if (i > j) {
                    rightSmaller += rating[i] > rating[j] ? 1 : 0;
                    rightGreater += rating[i] < rating[j] ? 1 : 0;
                }
            }
            
            res += leftSmaller * rightGreater + leftGreater * rightSmaller;
        }
        
        return res;
    }
}