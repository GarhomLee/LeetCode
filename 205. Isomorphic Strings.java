https://leetcode.com/problems/isomorphic-strings/

// 类似290. Word Pattern的做法，维护char数组和Set来判断s和t是不是bijection

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] mapping = new char[128];
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i), tChar = t.charAt(i);
            if (mapping[sChar] == 0) {
                if (set.contains(tChar)) return false;
                else {
                    mapping[sChar] = tChar;
                    set.add(tChar);
                }
            } else if (mapping[sChar] != tChar) return false;
        } 
        return true;
    }
}