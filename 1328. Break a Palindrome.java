https://leetcode.com/problems/break-a-palindrome/

思路：找规律
        特殊情况：只有当input长度为1，才会没法更改字符使得符合题意。
        由于是palindrome，先找到第一个非'a'的字符，变为'a'。
        如果全是'a'，那么只需要更改【最后一个'a'】，使得符合lexicographically smallest possible string。
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public String breakPalindrome(String palindrome) {
        // edge can
        if (palindrome.length() <= 1) {
            return "";
        }
        
        // change the first non-'a' to 'a'
        boolean isChanged = false;
        StringBuilder sb = new StringBuilder(palindrome);
        for (int i = 0; i < sb.length() / 2 && !isChanged; i++) {
            char c = sb.charAt(i);
            if (c == 'a') continue;
            
            sb.setCharAt(i, 'a');
            isChanged = true;
        }
        
        // all chars are 'a', then just change the last 'a' to 'b' 
        if (!isChanged && sb.length() >= 2) {
            int index = sb.length() - 1;
            sb.setCharAt(index, 'b');
            isChanged = true;
        }
        
        return sb.toString();
    }
}