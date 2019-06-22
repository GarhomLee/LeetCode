https://leetcode.com/problems/path-sum-ii/

// 思路：DFS，类似Backtracking，可以用模版稍作修改
//      goal:到达leaf node，且root.val等于剩余的sum
//      choices:左子树和右子树
//      constraints:root不能为空
// 犯错点：1.sum < 0不能作为终止判断条件，因为题目没有规定节点的值为正或负
//        2.在所有的list.add()操作后面都要有list.remove()操作来重置list

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(TreeNode root, int sum, List<Integer> list) {
        /* corner case, constraints */
        //if (root == null || sum < 0) return;  // {Mistake 1}
        if (root == null) return;  // {Correction 1}
        /* goal */
        if (isLeaf(root) && root.val == sum) {
            list.add(root.val);
            res.add(new ArrayList(list));
            // {Mistake 2}
            list.remove(list.size() - 1);  // {Correction 2: since an element is added in this step, reset is needed}
            return;
        }
        list.add(root.val);
        /* choices */
        dfs(root.left, sum - root.val, list);
        dfs(root.right, sum - root.val, list);
        list.remove(list.size() - 1);  // reset
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}