https://leetcode.com/problems/ransom-note/

// Character to Integer mapping的模板。
// corner cases：如果ransomNote字符比magazine多，那么一定不能组成ransomNote，直接返回false。

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] mapping = new int[128];
        for (char c: magazine.toCharArray()) {
            mapping[c]++;
        }
        for (char c: ransomNote.toCharArray()) {
            if (mapping[c] <= 0) return false;
            mapping[c]--;
        }
        return true;
    }
}