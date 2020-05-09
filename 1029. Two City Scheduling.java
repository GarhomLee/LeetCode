https://leetcode.com/problems/two-city-scheduling/

idea: Greedy. Referring to: https://leetcode.com/problems/two-city-scheduling/solution/
time complexity: O(n log n)
space complexity: O(n) for sorting, O(1) for iteration

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        // sort ascendingly by price diff between city A and city B
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int n = costs.length / 2, total = 0;
        for (int i = 0; i < n; i++) {
            total += costs[i][0] + costs[i + n][1];
        }
        
        return total;
    }
}