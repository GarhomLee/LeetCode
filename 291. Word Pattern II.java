https://leetcode.com/problems/word-pattern-ii/

// 思路：Backtracking (DFS) + HashMap
//         HashMap中，key为pattern中的字符，value为str中对应的子串。
//         递归函数定义：boolean dfs(String p, String s, int pi, int si)，表示用p[pi:p.length)
//             和s[si:s.length)是否能得到符合题意的word pattern。
//         终止条件：1）pi == p.length() && si == s.length()，同时完成对p和s的遍历，即找到了有效结果，返回true。
//                 2）在不满足1）时，pi == p.length() || si == s.length()，说明p或s提前结束了遍历，为无效结果，返回false。
//         递归过程：Goal：即终止条件。
//                 Choices：以从si+1开始到length为结尾的子串，即s[si:si+1)到s[si:s.length)。
//                 Constraints：子串已经在HashMap中，不符合bijection，因此跳过这个子串，si+1.
// 时间复杂度：O(p ^ s), p=pattern.length(), s=str.length()
// 空间复杂度：O(p), p=pattern.length()
// 犯错点：1.细节错误：由于是bijection，所以pattern中的字符要和str中分割出的子串【一一对应】。如pattern="ab"，
//             str="ccc"，不能出现'a'->"c", 'b'->"c"，但可以有'a'->"c", 'b'->"cc"。因此，如果str中分割出的子串
//             已经存在，只需要跳过，分割出更长的子串，不需要直接返回false。
//         2.细节错误：对于第一次出现的pattern字符c，在backtracking结束后要将c从HashMap中删除，否则会影响
//             下一次backtracking的过程。

class Solution {
    Map<Character, String> map = new HashMap<>();
    
    public boolean wordPatternMatch(String pattern, String str) {
        return dfs(pattern, str, 0, 0);
    }
    
    private boolean dfs(String p, String s, int pi, int si) {
        if (pi == p.length() && si == s.length()) {
            return true;
        }
        if (pi == p.length() || si == s.length()) {
            return false;
        }
        
        char c = p.charAt(pi);
        if (map.containsKey(c)) {
            String match = map.get(c);
            return s.startsWith(match, si) && dfs(p, s, pi + 1, si + match.length());
        } 
        
        for (int end = si + 1; end <= s.length(); end++) {
            String match = s.substring(si, end);
            //if (map.containsValue(match)) return false;  // {Mistake 1}
            if (map.containsValue(match)) continue;  // {Correction 1}
            
            map.put(c, match);
            if (dfs(p, s, pi + 1, end)) {
                return true;
            }
        }
        
        // {Mistake 2}
        map.remove(c);  // {Correction 2}
        
        return false;
    }
}