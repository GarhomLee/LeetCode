https://leetcode.com/problems/string-matching-in-an-array/

idea: Brute force
time complexity: O(n^2 * s)
space complexity: O(1)

class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> ret = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && words[j].indexOf(words[i]) >= 0) {
                    ret.add(words[i]);
                    break;
                }
            }
        }
        
        return ret;
    }
}