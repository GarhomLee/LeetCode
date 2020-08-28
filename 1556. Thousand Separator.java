https://leetcode.com/problems/thousand-separator/

idea: Math
time complexity: O(log n)
space complexity: O(log n)

class Solution {
    public String thousandSeparator(int n) {
        if (n == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (n != 0) {
            sb.insert(0, n % 10);
            n /= 10;
            count++;
            if (count % 3 == 0) {
                sb.insert(0, '.');
            }
        }
        
        if (sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
}