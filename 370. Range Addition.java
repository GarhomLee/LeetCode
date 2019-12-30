https://leetcode.com/problems/range-addition/

思路：Sweep Line

时间复杂度：O(n + l), n=updates.length, l=res.length
空间复杂度：O(l), l=res.length

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0], end = update[1], incr = update[2];
            res[start] += incr;
            if (end + 1 < length) {
                res[end + 1] += -incr;
            }
        }
        
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        
        return res;
    }
}