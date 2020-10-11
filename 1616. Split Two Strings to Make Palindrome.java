https://leetcode.com/problems/split-two-strings-to-make-palindrome/

idea: Greedy
    -First, find the longest prefix A that matches suffix B.
    -Then, check the remaining inner part of A and B.
time complexity: O(n)
space complexity: O(1)

class Solution {
    // check if s[left:right] is palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // helper method to find longest prefix A that matches suffix B
    private boolean helper(String a, String b) {
        int n = a.length();
        int left = 0, right = n - 1;
        while (left < right && a.charAt(left) == b.charAt(right)) {
            left++;
            right--;
        }
        
        return isPalindrome(a, left, right) || isPalindrome(b, left, right);
    }
    
    public boolean checkPalindromeFormation(String a, String b) {
        return helper(a, b) || helper(b, a);
    }
}