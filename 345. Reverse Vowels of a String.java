https://leetcode.com/problems/reverse-vowels-of-a-string/

// Two pointers问题。
// 注意：（1）大写的AEIOU也要考虑。
//      （2）查找vowel可以用trick："aeiouAEIOU".indexOf(c)，或者用Set将查找降至O(1)时间。

class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return "";
        char[] sArray = s.toCharArray();
        int left = 0, right = sArray.length - 1;
        while (left < right) {
            while (left < right && "aeiouAEIOU".indexOf(sArray[left]) < 0) left++;
            while (left < right && "aeiouAEIOU".indexOf(sArray[right]) < 0) right--;
            swap(sArray, left++, right--);
        }
        return new String(sArray);
    }
    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i]= s[j];
        s[j] = temp;
    }
}