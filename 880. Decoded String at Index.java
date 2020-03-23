https://leetcode.com/problems/decoded-string-at-index/

solution: work backwards. Reference: https://leetcode.com/problems/decoded-string-at-index/solution/
Time Complexity: O(N), where N is the length of S.
Space Complexity: O(1)

class Solution {
    public String decodeAtIndex(String S, int K) {
        long count = 0;
        for (char c : S.toCharArray()) {
            if (Character.isDigit(c)) {
                count *= (c - '0');
            } else {
                count++;
            }
        }
        
        K--;    // transform K into index
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (Character.isDigit(c)) {
                count /= (c - '0');
                K %= count;
            } else if (count == (K + 1)) {
                    return c+"";
                }
                
                count--;
            }
        }
        
        return "";
    }
}