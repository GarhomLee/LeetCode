https://leetcode.com/problems/delete-nodes-and-return-forest/

思路：DFS (postorder traversal)，视频讲解：https://www.youtube.com/watch?v=SEW3Vofoj_k
        递归函数实现一个功能，而结果的更新在递归过程中，却和递归函数的返回值不直接关联。
        递归函数定义：TreeNode dfs(TreeNode node, Set<Integer> delSet)，表示对以node为根的树进行
            递归操作后，返回的是node自己（没被删）或者null（已被删）。
        终止条件：node == null，直接返回null。
        递归过程：后序遍历，先对左右子树调用递归函数处理完，然后对node进行判断。
            如果node要被删掉，那么就要把【处理过的左右子节点中非空的节点加入res列表】，表示记录下它们为
            forest里的节点，然后返回null。这样node的parent节点将会拿到null，因此能直接将parent的
            子节点更新，断开连接。
            如果node不需要删掉，那么左右子节点肯定不会在forest里，因此只需要返回自己。
        特殊情况：对于root，因为没有parent节点来判断它是否能加入res，因此需要在最后单独判断。
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    List<TreeNode> res = new ArrayList<>();
    
    private TreeNode dfs(TreeNode node, Set<Integer> delSet) {
        if (node == null) return null;
        
        node.left = dfs(node.left, delSet);
        node.right = dfs(node.right, delSet);
        
        if (delSet.contains(node.val)) {
            if (node.left != null) {
                res.add(node.left);
            }
            if (node.right != null) {
                res.add(node.right);
            }
            return null;
        }
        
        return node;
    }
    
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> delSet = new HashSet<>();
        for (int num : to_delete) {
            delSet.add(num);
        }
        
        dfs(root, delSet);
        if (root != null && !delSet.contains(root.val)) {
            res.add(root);
        }
        
        return res;
    }
}