https://leetcode.com/problems/count-unhappy-friends/

idea: Brute Force
time complexity: O(n^3)
space complexity: O(n)

class Solution {
    private boolean isUnhappy(int currId, int pairedId, int[][] preferences, int[] pairArr) {
        for (int currPreferredId : preferences[currId]) {
            if (pairedId == currPreferredId) break; // stop if the preference has reached
            
            for (int nextPreferredId : preferences[currPreferredId]) {
                if (nextPreferredId == currId) {
                    return true;
                } else if (nextPreferredId == pairArr[currPreferredId]) {
                    break;  // jump to the next interation and find the next possible unhappy friend
                }
            }
        }
        
        return false;
    }
    
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int res = 0;
        int[] pairArr = new int[n];
        for (int[] pair : pairs) {
            pairArr[pair[0]] = pair[1];
            pairArr[pair[1]] = pair[0];
        }
        
        for (int[] pair : pairs) {
            res += isUnhappy(pair[0], pair[1], preferences, pairArr) ? 1 : 0;
            res += isUnhappy(pair[1], pair[0], preferences, pairArr) ? 1 : 0;
        }
        
        return res;
    }
}