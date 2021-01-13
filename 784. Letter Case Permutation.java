https://leetcode.com/problems/letter-case-permutation/

idea: Backtracking
time complexity: O(2^n)
space complexity: O(n)

class Solution {
    List<String> res = new ArrayList<>();
    
    private void helper(String s, int idx, StringBuilder sb) {
        if (idx == s.length()) {
            res.add(sb.toString());
            return;
        }
        
        char c = s.charAt(idx);
        sb.append(c);
        if (Character.isDigit(c)) {
            helper(s, idx + 1, sb);
        } else {
            sb.setCharAt(sb.length() - 1, Character.toLowerCase(c));
            helper(s, idx + 1, sb);
            sb.setCharAt(sb.length() - 1, Character.toUpperCase(c));
            helper(s, idx + 1, sb);
        }
        
        sb.deleteCharAt(sb.length() - 1);
    }
    
    public List<String> letterCasePermutation(String S) {
        helper(S, 0, new StringBuilder());
        return res;
    }
}