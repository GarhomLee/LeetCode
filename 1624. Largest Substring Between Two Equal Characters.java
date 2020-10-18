https://leetcode.com/problems/largest-substring-between-two-equal-characters/

idea: Info Cache
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] indices = new int[26];
        Arrays.fill(indices, -1);
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (indices[c - 'a'] == -1) {
                indices[c - 'a'] = i;
            } else {
                res = Math.max(res, i - indices[c - 'a'] - 1);
            }
        }
        
        return res;
    }
}