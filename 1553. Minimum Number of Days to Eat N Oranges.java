https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/

idea: Recursion with Memoization. Refer to: https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/discuss/794162/C%2B%2BJavaPython-5-lines
time complexity: O(log n)
space complexity: O(log n)

class Solution {
    private int dfs(int n, Map<Integer, Integer> dp) {
        if (n <= 1) {
            return 1;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        
        // in optimal choice, just eat the remainder (n % 2) or (n % 3) to skip some dfs
        int res = 1 + Math.min((n % 2) + dfs(n/2, dp), (n % 3) + dfs(n/3, dp));
        
        dp.put(n, res);
        return res;
    }
    
    public int minDays(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        return dfs(n, dp);
    }
}