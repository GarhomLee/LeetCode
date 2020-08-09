https://leetcode.com/problems/minimum-window-subsequence/

solution1: Binary Search
time complexity: O(s*t*log s)
space complexity: O(s)

class Solution {
    public String minWindow(String S, String T) {
        int minLen = Integer.MAX_VALUE;
        String ret = "";
        TreeSet<Integer>[] indices = new TreeSet[128];
        for (int i = 0; i < 128; i++) {
            indices[i] = new TreeSet<>();
        }
        
        for (int i = 0; i < S.length(); i++) {
            indices[S.charAt(i)].add(i);
        }
        
        for (int left : indices[T.charAt(0)]) {
            Integer right = left - 1;
            for (char c : T.toCharArray()) {
                right = indices[c].higher(right);
                if (right == null) break;
            }
            
            if (right != null && right - left + 1 < minLen) {
                minLen = right - left + 1;
                ret = S.substring(left, right + 1);
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : ret;
    }
}


solution2: DP. Referring to: https://leetcode.com/problems/minimum-window-subsequence/discuss/109362/Java-Super-Easy-DP-Solution-(O(mn))
time complexity: O(s*t)
space complexity: O(s*t)