https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/

// 思路：Math，等差数列公式
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.思路错误：对n个相同字符可以组成的不同substring，只需要按长度从1到len用等差求和公式即可。

class Solution {
    public int countLetters(String S) {
        int len = S.length(), res = 0;
        int left = 0;
        while (left < len) {
            char c = S.charAt(left);
            int right = left;
            while (right + 1 < len && S.charAt(right + 1) == c) {
                right++;
            }
            
            //int count = count(right - left + 1);  // {Mistake 1}
            int count = (1 + right - left + 1) * (right - left + 1) / 2;  // {Correction 1}
            res += count;
            left = right + 1;
        }

        return res;
        
    }
}