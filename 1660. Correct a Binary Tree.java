https://leetcode.com/problems/correct-a-binary-tree/

idea: BFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    public TreeNode correctBinaryTree(TreeNode root) {
        Set<TreeNode> seen = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        seen.add(root);
        boolean found = false;

        // bfs from right to left
        while (!queue.isEmpty() && !found) {
            TreeNode curr = queue.poll();
            if (curr.right != null) {
                if (seen.contains(curr.right.right)) {
                    curr.right = null;
                    found = true;
                } else {
                    queue.offer(curr.right);
                    seen.add(curr.right);
                }
            }
            if (curr.left != null) {
                if (seen.contains(curr.left.right)) {
                    curr.left = null;
                    found = true;
                } else {
                    queue.offer(curr.left);
                    seen.add(curr.left);
                }
            }
        }
        
        return root;
    }
}