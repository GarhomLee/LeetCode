https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/

idea: DFS
time complexity: O(nk), k=how many distinct numbers
space complexity: O(k + h)
optimization: use HashSet or Bit Manipulation to store the occurrence of odd number

class Solution {
    private boolean isPalindromic(int[] count) {
        int odd = 0;
        for (int num : count) {
            odd += num % 2 == 1 ? 1 : 0;
        }
        
        return odd <= 1;
    }
    
    private int dfs(TreeNode node, int[] count) {
        if (node == null) {
            return 0;
        }
        
        int total = 0;
        count[node.val]++;
        if (node.left == null && node.right == null) {
            // leaf node
            total += isPalindromic(count) ? 1 : 0;
        } else {
            total += dfs(node.left, count) + dfs(node.right, count);
        }
        
        count[node.val]--;
        return total;
    }
    
    public int pseudoPalindromicPaths (TreeNode root) {
        int[] count = new int[10];
        
        return dfs(root, count);
    }
}