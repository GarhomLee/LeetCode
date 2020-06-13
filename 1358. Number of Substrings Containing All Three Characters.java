https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/discuss/516977/JavaC%2B%2BPython-Easy-and-Concise

solution1: Info Cache
    -Record the last occurrence of each char while iterating the string.
    -Find the one with the minimum index idx, then the left boundary of valid substring can be any one in [0:idx],
     so the count is idx+1.
time complexity: O(n)
space compexity: O(1)

class Solution {
    public int numberOfSubstrings(String s) {
        int aIdx = -1, bIdx = -1, cIdx = -1;
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                aIdx = i;
            } else if (c == 'b') {
                bIdx = i;
            } else if (c == 'c') {
                cIdx = i;
            }
            
            res += 1 + Math.min(aIdx, Math.min(bIdx, cIdx));
        }
        
        return res;
    }
}


solution2: Sliding Window
time complexity:
space compexity:

