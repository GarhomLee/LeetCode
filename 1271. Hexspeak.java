https://leetcode.com/problems/hexspeak/

// 思路：Math + String
// 时间复杂度：O(log n)
// 空间复杂度：O(log n)

class Solution {
    public String toHexspeak(String s) {
        char[] letters = new char[]{'O', 'I' ,'A', 'B', 'C', 'D', 'E', 'F'};
        String res = "";
        long num = Long.parseLong(s);
        while (num > 0) {
            int remainder = (int) (num % 16);
            if (remainder >= 2 && remainder <= 9) {
                return "ERROR";
            }
            char letter = remainder < 2 ? letters[remainder] : letters[remainder - 8];
            res = letter + res;
            num /= 16;
        }
        return res;
    }
}