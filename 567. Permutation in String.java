https://leetcode.com/problems/permutation-in-string/

// 思路：sliding window，438. Find All Anagrams in a String的简化版。
//         题目可以转化为s2中是否出现长度为s1的窗口，且窗口中字符的种类和数量和s1相同，对于各字符的位置没有要求。
//         维护count数组，表示s1中出现的字符的种类和数量。同时维护变量total，表示s1的字符总数。
//         遍历s2，count数组中对应当前字符s2[right]的数量-1，表示已经处理了。如果字符在s1出现过，那么total--。
//         当total==0，表示当前窗口中包含了所有s1的字符，那么先判断窗口大小是否等于s1长度，如果是，那么符合题意，
//         直接返回true。
//         如果窗口大小不等于s1长度，那么尝试缩小窗口，右移左边缘left，对应的count++，表示从窗口中移除。如果s2[left]
//         在s1出现过，那么，total++。重复这个过程直至total!=0，表示当前窗口缺少s1的某些字符，这时需要重新移动右边缘。

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] count = new int[26];
        int total = 0;
        for (char c: s1.toCharArray()) {
            count[c - 'a']++;
            total++;
        }
        
        for (int left = 0, right = 0; right < s2.length(); right++) {
            char c = s2.charAt(right);
            if (count[c - 'a'] > 0) total--;
            count[c - 'a']--;
            while (total == 0) {
                if (right - left + 1 == s1.length()) {
                    return true;
                }
                count[s2.charAt(left) - 'a']++;
                if (count[s2.charAt(left++) - 'a'] > 0) total++;
            }
        }
        
        return false;
    }
}