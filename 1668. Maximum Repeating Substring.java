https://leetcode.com/problems/maximum-repeating-substring/discuss/952025/C%2B%2B-short-easy-using-find

idea: String built-in indexOf()
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int maxRepeating(String sequence, String word) {
        int n = word.length(), repeat = 0;
        String w = word;
        while (sequence.indexOf(w) >= 0) {
            repeat++;
            w += word;
        }
        
        return repeat;
    }
}