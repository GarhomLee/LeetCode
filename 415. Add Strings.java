https://leetcode.com/problems/add-strings/

idea: StringBuilder, from right to left, add carry over, then reverse and return.

class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int idx1 = num1.length() - 1, idx2 = num2.length() - 1;
        int carry = 0;
        while (idx1 >= 0 || idx2 >= 0) {
            if (idx1 >= 0) {
                carry += num1.charAt(idx1--) - '0'; 
            }
            if (idx2 >= 0) {
                carry += num2.charAt(idx2--) - '0';
            } 
            
            sb.append(carry % 10);
            carry = carry / 10;
        }
        
        if (carry > 0) {
            sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
}