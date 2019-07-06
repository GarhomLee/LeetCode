https://leetcode.com/problems/reverse-string-ii/

// 思路：Two pointers。
//      每次处理2*k个数，但只将前k个数进行翻转，然后处理下一批数。
// 犯错点：1.如果循环中直接移动left指针，那么在更新判断条件left += 2 * k的时候并不能更新成想要的结果。

class Solution {
    public String reverseStr(String s, int k) {
        char[] sArray = s.toCharArray();
        
        //for (int left  = 0; left < sArray.length; left += 2 * k) {  // {Mistake 1}
        for (int start  = 0; start < sArray.length; start += 2 * k) {  // {Correction 1}
            // {Mistake 1}
            int left = start;  // {Correction 1}
            int right = Math.min(sArray.length - 1, left + k - 1);
            swap(sArray, left++, right--);
        }
        return new String(sArray);
    }
    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}