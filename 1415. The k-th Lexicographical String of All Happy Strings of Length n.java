https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/

idea: Backtracking
time complexity: O(3*2^(n-1))
space complexity: O(3*2^(n-1))
optimization?

class Solution {
    private void dfs(StringBuilder sb, int usedIdx, int n, char[] chars, List<String> list) {
        // goal
        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }
        
        // choice
        for (int i = 0; i < chars.length; i++) {
            if (i == usedIdx) continue; // constraint
            sb.append(chars[i]);
            dfs(sb, i, n, chars, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    
    public String getHappyString(int n, int k) {
        char[] chars = {'a', 'b', 'c'};
        StringBuilder sb = new StringBuilder();
                
        List<String> list = new ArrayList<>();
        dfs(sb, -1, n, chars, list);
        if (k > list.size()) {
            return "";
        }
        
        Collections.sort(list);
        return list.get(k - 1);
    }
}