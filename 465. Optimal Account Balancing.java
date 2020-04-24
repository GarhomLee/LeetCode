https://leetcode.com/problems/optimal-account-balancing/

idea: Backtracking. Referring to: https://leetcode.com/problems/optimal-account-balancing/discuss/130895/Recursion-Logical-Thinking
time complexity: O()
space complexity: O()

class Solution {
    /* return the minimum transaction steps given amount[currIndex:len) */
    private int dfs(int currIndex, int[] amount) {
        // goal (base case)
        if (currIndex == amount.length) {
            return 0;
        }
        // optimization
        if (amount[currIndex] == 0) {
            return dfs(currIndex + 1, amount);
        }
        
        int min = Integer.MAX_VALUE;
        // choices
        for (int nextIndex = currIndex + 1; nextIndex < amount.length; nextIndex++) {
            // constraints: same sign (both pay or both paid) should NOT have further transaction
            if (amount[currIndex] * amount[nextIndex] < 0) {
                amount[nextIndex] += amount[currIndex];
                min = Math.min(min, 1 + dfs(currIndex + 1, amount));
                amount[nextIndex] -= amount[currIndex]; // reset
            }
        }
        
        
        return min;
    }
    
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            int from = t[0], to = t[1], money = t[2];
            map.put(from, map.getOrDefault(from, 0) - money);
            map.put(to, map.getOrDefault(to, 0) + money);
        }
        
        int[] amount = new int[map.size()];
        int i = 0;
        for (int key : map.keySet()) {
            amount[i++] = map.get(key);
        }
        
        return dfs(0, amount);
    }
}