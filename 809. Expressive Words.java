https://leetcode.com/problems/expressive-words/

idea: Two (four) Pointers
time complexity: O(n*l)
space complexity: O(1)

class Solution {
    private boolean isStretchy(String S, String word) {
        if (S.length() < word.length()) {
            return false;
        }
        
        int sLeft = 0;
        int wLeft = 0;
        while (sLeft < S.length() && wLeft < word.length()) {
            char cs = S.charAt(sLeft), cw = word.charAt(wLeft);
            if (cs != cw) {
                return false;
            }
            
            int sRight = sLeft, wRight = wLeft;
            while (sRight < S.length() && cs == S.charAt(sRight)) {
                sRight++;
            }
            while (wRight < word.length() && cw == word.charAt(wRight)) {
                wRight++;
            }
            
            
            if ((sRight - sLeft > wRight - wLeft && sRight - sLeft < 3)
                || sRight - sLeft < wRight - wLeft) {
                return false;
            }
            
            sLeft = sRight;
            wLeft = wRight;
        }
        
        return sLeft == S.length() && wLeft == word.length();
    }
    
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String w : words) {
            res += isStretchy(S, w) ? 1 : 0;
        }
        
        return res;
    }
}
