https://leetcode.com/problems/greatest-common-divisor-of-strings/

idea: Math + String ops
time complexity: O((log l) + l + l)
space complexity: O(l)

class Solution {
    private int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        
        return gcd(b % a, a);
    }
    
    private boolean isDivisor(String candidate, String s) {
        int n = s.length(), k = candidate.length();
        for (int i = 0; i < n; i += k) {
            if (!s.startsWith(candidate, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    public String gcdOfStrings(String str1, String str2) {
        int gcLen = gcd(str1.length(), str2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gcLen; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return "";
            }
            
            sb.append(str1.charAt(i));
        }
        
        String candidate = sb.toString();
        return isDivisor(candidate, str1) && isDivisor(candidate, str2) ? candidate : "";
    }
}