https://leetcode.com/problems/number-of-ways-to-split-a-string/

idea: Greedy?
    -Only when there are counts of 1 that is multiple of 3 that there is an answer. Otherwise, return 0.
    -Be aware of big number, thus long type should be used.
time complexity: O(n)
space complexity: O(n)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int numWays(String s) {
        int n = s.length();
        if (n <= 2) {
            return 0;    
        }
        
        List<Integer> oneIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                oneIndices.add(i);
            }
        }
        int size = oneIndices.size();
        
        if (size % 3 != 0) {
            return 0;
        }
        
        if (size == 0) {
            return (int) (((long) (1 + n - 2)) * (n - 2) / 2 % MOD);
        }
        
        long left = oneIndices.get(size / 3) - oneIndices.get(size / 3 - 1);
        long right = oneIndices.get(2 * size / 3) - oneIndices.get(2 * size / 3 - 1);
        return (int) (left * right % MOD);
    }
}