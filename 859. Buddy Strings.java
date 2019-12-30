https://leetcode.com/problems/buddy-strings/

思路：对String的不同情况分类讨论
        变量count数组
时间复杂度：O(n)
空间复杂度：O(128) = O(1)

class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        
        int[] count = new int[128];
        int mismatch = 0, index1 = -1, index2 = -1;
        for (int i = 0; i < A.length(); i++) {
            char c1 = A.charAt(i), c2 = B.charAt(i);
            if (c1 != c2) {
                mismatch++;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    index2 = i;
                }
            } else {
                count[c1]++;
            }
        }
        
        if (mismatch == 0) {
            for (int num : count) {
                if (num >= 2) {
                    return true;
                }
            }
            return false;
        }
        
        return mismatch == 2 && A.charAt(index2) == B.charAt(index1) && A.charAt(index1) == B.charAt(index2);
    }
}