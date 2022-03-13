https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/

idea: Greedy
    -Maintain running sum currTotal and max currMax, the min cost should be currTotal - currMax for
     all possible consecutive substrings.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;
        int ret = 0, currSum = 0, currMax = 0;
        for (int i = 0; i <= n; i++) {
            // when ret updates
            if (i == n || (i > 0 && colors.charAt(i) != colors.charAt(i - 1))) {
                ret += currSum - currMax;
                currSum = 0;
                currMax = 0;
            } 
            
            // update currSum and currMax during each iteration
            if (i < n) {
                currSum += neededTime[i];
                currMax = Math.max(currMax, neededTime[i]);
            }
        }
        
        return ret;
    }
}