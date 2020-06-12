https://leetcode.com/problems/check-if-a-string-is-a-valid-sequence-from-root-to-leaves-path-in-a-binary-tree/

idea: DFS
time complexity: O(n)
space complexity: O(h)

class Solution {
    private boolean dfs(TreeNode node, int[] arr, int idx) {
        int n = arr.length;
        if (node == null || idx == n || node.val != arr[idx]) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return idx == n - 1;
        }
        
        return dfs(node.left, arr, idx + 1) || dfs(node.right, arr, idx + 1);
    }
    
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }
}