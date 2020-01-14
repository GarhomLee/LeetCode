https://leetcode.com/problems/brace-expansion/

思路：Backtracking

时间复杂度：
空间复杂度：

class Solution {
    private void dfs(String S, int index, StringBuilder sb, List<String> res) {
        if (index == S.length()) {
            res.add(sb.toString());
            return;
        }
        
        if (S.charAt(index) == '{') {
            int right = S.indexOf('}', index + 1);
            for (char c : S.substring(index + 1, right).toCharArray()) {
                if (c == ',') continue;
                sb.append(c);
                dfs(S, right + 1, sb, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            int right = S.indexOf('{', index);
            if (right < 0) {
                right = S.length();
            }
            String substr = S.substring(index, right);
            sb.append(substr);
            dfs(S, right, sb, res);
            sb.delete(sb.length() - substr.length(), sb.length());
        }
        
    }
    
    public String[] expand(String S) {
        
        List<String> res = new ArrayList<>();
        dfs(S, 0, new StringBuilder(), res);
        Collections.sort(res);
        return res.toArray(new String[0]);
    }
}