https://leetcode.com/problems/rotate-string/

// 思路：常规比较
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public boolean rotateString(String A, String B) {
        // edge cases
        if (A.isEmpty() && B.isEmpty()) {
            return true;
        }
        if (A.length() != B.length()) {
            return false;
        }
        
        int index = B.indexOf(A.charAt(0));
        while (index >= 0) {
            String newStr = B.substring(index) + B.substring(0, index);
            if (A.equals(newStr)) {
                return true;
            }
            index = B.indexOf(A.charAt(0), index + 1);
        }
        
        return false;
    }
}