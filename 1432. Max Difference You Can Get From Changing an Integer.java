https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/

idea: Greedy
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxDiff(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        char cBig = '?', cSmall = '?';
        boolean isZero = false;
        StringBuilder big = new StringBuilder(s);
        StringBuilder small = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cBig == '?' && c != '9') {
                // set this char to '9'
                cBig = c;
            }
            if (cBig == c) {
                big.setCharAt(i, '9');
            }
            
            if (cSmall == '?') {
                if (i == 0 && c != '1') {
                    // set this char to '1'
                    cSmall = c;
                    isZero = false;
                } else if (i > 0 && c != '1' && c != '0') {
                    // set this char to '0'
                    cSmall = c;
                    isZero = true;
                }
            }
            if (cSmall == c) {
                small.setCharAt(i, isZero ? '0' : '1');
            }
        }
        
        return Integer.parseInt(big.toString()) - Integer.parseInt(small.toString());
    }
}