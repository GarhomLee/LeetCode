https://leetcode.com/problems/minimum-cost-to-cut-a-stick/

idea: Recursion with Memoization
time complexity: O(l^3), l=cuts.length
space complexity: O(l^2), l=cuts.length

class Solution {
    private int dfs(int low, int high, int[] cuts, int startIdx, Map<Integer, Map<Integer, Integer>> dp) {
        int res = 0;
        if (startIdx == cuts.length || cuts[startIdx] == high) {
            return 0;
        }
        if (dp.containsKey(low) && dp.get(low).containsKey(high)) {
            return dp.get(low).get(high);
        }       
        
        dp.putIfAbsent(low, new HashMap<>());
        int min = Integer.MAX_VALUE;
        for (int i = startIdx; i < cuts.length && cuts[i] < high; i++) {
            min = Math.min(min, dfs(low, cuts[i], cuts, startIdx, dp) + dfs(cuts[i], high, cuts, i + 1, dp));
        }

        min += high - low;
        dp.get(low).put(high, min);
        return min;
    }
    
    public int minCost(int n, int[] cuts) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();    // low -> (high -> min cost)
        Arrays.sort(cuts);
        
        return dfs(0, n, cuts, 0, dp);
    }
}