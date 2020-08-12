https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/

idea: DFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    private int dfs(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int mid = (1 << (n - 1));
        if (k == mid) {
            return 1;
        }
        
        if (k > mid) {
            return 1 - dfs(n - 1, 2 * mid - k);
        }
        
        return dfs(n - 1, k);
    }
    
    
    public char findKthBit(int n, int k) {
        return (char) (dfs(n, k) + '0');
    }
}