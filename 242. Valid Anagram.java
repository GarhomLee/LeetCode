https://leetcode.com/problems/valid-anagram/

// 1）当s和t长度不相同，直接返回false
// 2）利用类似sliding window的策略，数s中各个字符出现的次数，然后遍历t，如果count <= 0，说明有mismatch，返回false

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[128];
        int total = 0;
        for (char c: s.toCharArray()) {
            count[c]++;
        }
        for (char c: t.toCharArray()) {
            if (count[c] <= 0) return false;
            count[c]--;
        }
        return true;
    }
}