https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/

idea: String Simulation

class Solution {
    public String generateTheString(int n) {
        int k = ((n & 1) == 0) ? n - 1 : n;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append('a');
        }
        
        if (k == n - 1) {
            sb.append('b');
        }
        
        return sb.toString();
    }
}