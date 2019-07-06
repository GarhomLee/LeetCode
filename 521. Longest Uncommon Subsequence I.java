https://leetcode.com/problems/longest-uncommon-subsequence-i/

// 关键点：要理解题目的意思。
//        如果两个字符串相同，那么不存在只出现一次的子串，所以返回-1。
//        否则，只要取长度较长的字符串，以整个字符串为最大子串，那么这个子串一定只出现一次。即使两个字符串长度相等，
//        因为它们有不相同的字符，所以取任意一个字符串都可以。

class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        
        return Math.max(a.length(), b.length());
    }
}