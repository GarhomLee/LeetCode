https://leetcode.com/problems/reverse-string/

// Two pointers问题。

class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }
    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i]= s[j];
        s[j] = temp;
    }
}