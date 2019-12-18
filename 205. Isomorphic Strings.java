https://leetcode.com/problems/isomorphic-strings/

// 类似290. Word Pattern的做法，维护char数组和Set来判断s和t是不是bijection
// 思路：Hash Table
// 时间复杂度：O(n)
// 空间复杂度：O(n)

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


三刷：用array模拟hash table
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int n = s.length();
        int[] map1 = new int[128], map2 = new int[128];
        Arrays.fill(map1, -1);
        Arrays.fill(map2, -1);
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (map1[c1] == c2 && map2[c2] == c1) continue;
            
            if (map1[c1] == -1 && map2[c2] == -1) {
                map1[c1] = c2;
                map2[c2] = c1;
            } else {
                return false;
            }
        }
        return true;
    }
}