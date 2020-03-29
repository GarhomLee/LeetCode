

idea: Find the intrinsic rules and observations. Referring to: https://leetcode.com/problems/swap-adjacent-in-lr-string/solution/
time complexity: O(n)
space complexity: O(n)

class Solution {
    public boolean canTransform(String start, String end) {
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            // System.out.println("dddd");
            return false;
        }
        
        int j = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'L') continue;
            
            while (j < end.length() && end.charAt(j) != 'L') {
                j++;
            }
            
            if (i < j) {
                return false;
            }
            j++;
        }
        
        j = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'R') continue;
            
            while (j < end.length() && end.charAt(j) != 'R') {
                j++;
            }
            
            if (i > j) {
                return false;
            }
            j++;
        }
        
        return true;
    }
}