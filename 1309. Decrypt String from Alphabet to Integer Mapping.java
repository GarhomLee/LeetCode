https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/

思路：常规扫描遍历，分类讨论（分两类）
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0, len = s.length();
        while (i < len) {
            if (i + 2 < len && s.charAt(i + 2) == '#') {
                int num = Integer.valueOf(s.substring(i, i + 2)) - 1;
                char c = (char) ('a' + num);
                sb.append(c);
                i += 3;
            } else {
                char c = (char) ('a' + s.charAt(i) - '1');
                sb.append(c);
                i++;
            }
        }
        
        return sb.toString();
    }
}