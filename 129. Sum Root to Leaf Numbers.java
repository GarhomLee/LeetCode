https://leetcode.com/problems/sum-root-to-leaf-numbers/

// 解法一：Recursion+DFS
//        从root开始，每到一个新节点，当前的和currSum更新为currSum * 10 + root.val，直到到达leaf node。
// 犯错点：1.sum += currSum不能在root==null的时候操作，因为root==null不一定到了leaf node。即使到了leaf node，因为左右子树都为null，所以会做两次操作，导致结果出错。
//        2.如果到达了leaf node，currSum要更新后再加到sum里。

class Solution {
    int sum = 0;
    
    public int sumNumbers(TreeNode root) {
        sum(root, 0);
        return sum;
    }
    
    private void sum(TreeNode root, int currSum) {
        /* corner case */
        if (root == null) {
            //sum += currSum;  // {Mistake 1: if place sum operation here, it would be executed twice since there are two null children of leaf node}
            return;
        }
        
        // {Mistake 2}
        currSum = currSum * 10 + root.val;  // {Correction 2}
        /* leaf node */
        if (root.left == null && root.right == null) {  // {Correction 1}
            sum += currSum;
            return;
        }
        /* dfs */
        sum(root.left, currSum);
        sum(root.right, currSum);
    }
}


// 解法二：Iteration with Queue
//        类似level order traversal模版，在将子节点加入Queue前就利用子节点本身更新从root到子节点的currSum。当到达leaf node时更新结果sum。
// 犯错点：1.不能将val更新后用新的TreeNode加入Queue，因为这样会丢失子树的信息而变成leaf node。应该直接更新左右子节点的val。

class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {  // process current level
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {  // reach leaf node, update sum
                    sum += curr.val;
                }
                if (curr.left != null) {
                    //queue.offer(new TreeNode(curr.val * 10 + curr.left.val));  // {Mistake 1: the left and right child will be lost}
                    curr.left.val += curr.val * 10;  // {Correction 1: in-place update val}
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    //queue.offer(new TreeNode(curr.val * 10 + curr.right.val));  // {Mistake 1}
                    curr.right.val += curr.val * 10;  // {Correction 1}
                    queue.offer(curr.right);
                }
            }
        }
        return sum;
    }
}