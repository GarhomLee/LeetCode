https://leetcode.com/problems/valid-palindrome-ii/

思路：常规String遍历
        维护left和right指针，从0和s.length() - 1向中间扫描，找到第一个s[left]!=s[right]的位置，
        返回删除s[left]或s[right]的结果。
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return validate(s, left + 1, right) || validate(s, left, right - 1);
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    private boolean validate(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}