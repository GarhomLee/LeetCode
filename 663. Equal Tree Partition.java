https://leetcode.com/problems/equal-tree-partition/

idea: DFS (postorder traversal)
time complexity: O(n)
space complexity: O(n)

class Solution {
    Set<Integer> set = new HashSet<>();
    int n  = 0;
    
    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        n++;
        int left = helper(node.left), right = helper(node.right);
        if (node.left != null) {
            set.add(left);
        }
        if (node.right != null) {
            set.add(right);
        }
        int sum = node.val + left + right;
        return sum;
    }
    
    public boolean checkEqualTree(TreeNode root) {
        int sum = helper(root);
        
        return n > 1 && sum % 2 == 0 && set.contains(sum / 2);
    }
}