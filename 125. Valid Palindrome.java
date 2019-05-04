https://leetcode.com/problems/valid-palindrome/

// 解法一：Two pointers，先将String转换成lower case，然后left和right两个指针从两边向中间扫描
// 注意：toLowerCase()的【返回值是一个String】
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        String copy = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(copy.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(copy.charAt(right))) right--;
            if (copy.charAt(left++) != copy.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

// 解法二：把空格去掉，转成lower case，然后取reverse，变成常规的palindrome问题。

public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}