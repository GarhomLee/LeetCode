
https://leetcode.com/problems/perform-string-shifts/

idea: Math
time complexity: O(n+s), n=shift.length, s=s.length()
space complexity: O(s)

class Solution {
    public String stringShift(String s, int[][] shift) {
        int total = 0;  // total num of left shift
        for (int[] op : shift) {
            total += (op[0] == 0 ? op[1] : -op[1]);
        }
        
        total %= s.length();    // avoid out of range
        if (total < 0) {
            total = s.length() + total;
        }
        
        return s.substring(total) + s.substring(0, total);
    }
}