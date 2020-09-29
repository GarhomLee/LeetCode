https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/

idea: Backtracking
time complexity: O(2^n)
space complexity: O(n^2 * l), n=s.length(), l=average length of each substring

class Solution {
    private int dfs(String s, int start, Set<String> used) {
        int n = s.length();
        if (start == n) {
            return 0;
        }
        
        int res = -1;
        for (int end = start + 1; end <= n; end++) {
            String substr = s.substring(start, end);
            if (!used.contains(substr)) {
                used.add(substr);
                int next = dfs(s, end, used);
                if (next != -1) {
                    res = Math.max(res, 1 + next);
                }
                used.remove(substr);
            }
        }
        
        return res;
    }
    
    public int maxUniqueSplit(String s) {
        return dfs(s, 0, new HashSet<String>());
    }
}