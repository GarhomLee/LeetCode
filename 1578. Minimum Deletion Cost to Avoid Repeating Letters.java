https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/

idea: Greedy
    -Maintain running sum currTotal and max currMax, the min cost should be currTotal - currMax for
     all possible consecutive substrings.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minCost(String s, int[] cost) {
        int n = s.length(), res = 0;
        int currTotal = cost[0], currMax = cost[0];
        for (int i = 1; i <= n; i++) {
            if (i == n || s.charAt(i) != s.charAt(i - 1)) {
                res += currTotal - currMax;
                if (i < n) {
                    currTotal = cost[i];
                    currMax = cost[i];
                }
            } else {
                currTotal += cost[i];
                currMax = Math.max(currMax, cost[i]);
            }
        }
        return res;
    }
}