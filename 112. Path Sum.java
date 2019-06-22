https://leetcode.com/problems/path-sum/

// 解法一：Recursion
//        注意root == null时的结果为false。

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;  // Attention: special corner case
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}


// 解法二：Iteration
//        类似129. Sum Root to Leaf Numbers的Iteration写法，利用node本身来记录sum减去所有父节点后的余数的信息。

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Queue<TreeNode> queue = new LinkedList();
        if (root != null) {
            root.val = sum - root.val;  // use each node to carry the info of leftover
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (isLeaf(curr) && curr.val == 0) {
                    return true;
                }
                if (curr.left != null) {
                    curr.left.val = curr.val - curr.left.val;
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    curr.right.val = curr.val - curr.right.val;
                    queue.offer(curr.right);
                }
            }
        }
        return false;
    }
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}