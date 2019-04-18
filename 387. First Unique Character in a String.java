https://leetcode.com/problems/first-unique-character-in-a-string/

// 1）把s中character出现的次数存进数组
// 2）找到第一个count[c] == 1对应的index，否则返回-1

class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray()) {
            count[c]++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c] == 1) return i;
        }
        return -1;
    }
}