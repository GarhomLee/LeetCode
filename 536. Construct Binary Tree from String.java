https://leetcode.com/problems/construct-binary-tree-from-string/

idea: DFS
time complexity: O(n)
space complexity: O(n)?

class Solution {
    private int findRight(String s, int leftIdx) {
        int leftCount = 0, rightIdx = leftIdx;
        while (rightIdx < s.length()) {
            char c = s.charAt(rightIdx);
            if (c == '(') {
                leftCount++;
            } else if (c == ')') {
                leftCount--;
                if (leftCount == 0) break;
            }
            
            rightIdx++;
        }
        
        return rightIdx;
    }
    
    private TreeNode dfs(String s) {
        if (s.length() == 0) {
            return null;
        }
        
        int leftIdx = s.indexOf("(");
        if (leftIdx == -1) {
            return new TreeNode(Integer.parseInt(s));
        }
        
        int rightIdx = findRight(s, leftIdx);
        TreeNode node = new TreeNode(Integer.parseInt(s.substring(0, leftIdx)));
        node.left = dfs(s.substring(leftIdx + 1, rightIdx));
        if (rightIdx + 2 < s.length()) {
            node.right = dfs(s.substring(rightIdx + 2, s.length() - 1));
        }
        
        return node;
    }
    
    public TreeNode str2tree(String s) {
        return dfs(s);
    }
}