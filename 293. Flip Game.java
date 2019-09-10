https://leetcode.com/problems/flip-game/

// 解法一：遍历 + StringBuilder.setCharAt()
// 时间复杂度：O(n)
// 空间复杂度：O(n^2)

class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                res.add(set(new StringBuilder(s), i, i + 1));
            }
        }
        
        return res;
    }
    
    private String set(StringBuilder sb, int i, int j) {
        sb.setCharAt(i, '-');
        sb.setCharAt(j, '-');
        return sb.toString();
    }
}


解法二：遍历 + String.indexOf() + String.substring()