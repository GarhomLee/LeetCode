https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/

// 思路：常规扫描遍历
//      遍历List中的所有String t，判断t是否是s的subsequence，然后更新为最长的subsequence。
//      利用helper method判断t是否是s的subsequence，只需分别给s和t各一个指针，从左往右扫描，直到扫描完整个t，
//      或者扫描完s后t也被扫描完。
// 可优化：利用String.indexOf()而不是两个指针来判断s和t是否相等。

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String maxString = "";
        int maxLen = 0;
        for (String t: d) {
            if (!isSubsequence(s, t)) continue;
            if (t.length() > maxLen) {
                maxLen = t.length();
                maxString = t;
            } else if (t.length() == maxLen && t.compareTo(maxString) < 0) {
                maxString = t;
            }
        }
        
        return maxString;
    }
    /* evaluate if t is a subsequence of s */
    private boolean isSubsequence(String s, String t) {
        if (s.length() < t.length()) return false;
        int sIndex = 0, tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                tIndex++;
            }
            sIndex++;
        } 
        return tIndex == t.length();
    }
}