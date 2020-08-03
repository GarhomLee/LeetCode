https://leetcode.com/problems/shuffle-string/

idea: Brute Force
time complexity: O(n)
space complexity: O(n)

class Solution {
    public String restoreString(String s, int[] indices) {
        int n = indices.length;
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            int idx = indices[i];
            res[idx] = s.charAt(i);
        }
        
        return new String(res);
    }
}