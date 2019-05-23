https://leetcode.com/problems/house-robber-iii/

// 思路详解：https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem

// 解法一：Top-down Memoization
//         类似198. House Robber，但利用Map进行memoization。如果对某个TreeNode的最大收益进行过计算，那么用Map来存储这个TreeNode对应的最大收益信息。
//         如果没有计算过，那么就要比较取当前TreeNode和它grandchildren的总收益，以及不取当前TreeNode只取它children的总收益，然后利用Map将TreeNode
//         和计算的的最大收益对应起来。

class Solution {
    public int rob(TreeNode root) {
        return rob(root, new HashMap<TreeNode, Integer>());
    }
    private int rob(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);  // use info by memoization
        
        int val = root.val;  // val indicates maximum gain if current TreeNode is robbed
        if (root.left != null) val += rob(root.left.left, map) + rob(root.left.right, map);  // should evaluate if root.left is null or not before calculation
        if (root.right != null) val += rob(root.right.left, map) + rob(root.right.right, map);  // should evaluate if root.right is null or not before calculation
        int max = Math.max(val, rob(root.left, map) + rob(root.right, map));  // max indicates the maximum gain of current TreeNode after comparison
        map.put(root, max);  // memoization
        return max;
    }
}

// 解法二：Bottom-up Memoization，同时保留当前TreeNode是否被robbed的信息
//         类似于array-based robbery的一个DP问题，只是此时要同时考虑左右子树。对于每个node，维护一个数组，其中[0]表示当前node没有被robbed，[1]表示当前node被robbed了。
//         因此，[0]可以由左子树的两种状态最大值+右子树的两种状态最大值决定，而[1]只能由左子树[0]+右子树[0]得到（因为如果当前node被robbed了，相连的子树都不能被robbed）。最后返回max([0], [1])

class Solution {
    public int rob(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }
    private int[] dp(TreeNode root) {
        int[] res = new int[2];  // res array has two elements, where res[0] indicates no robbery at current TreeNode and res[1] indicates robbery
        if (root == null) return res;  // corner case
        
        int[] left = dp(root.left), right = dp(root.right);  // get the maximum gain from left and right child with and without robbery
        
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);  // if current TreeNode is not robbed, the maximum gain might come from left and right child with or without robbery, and pick the greater ones
        res[1] = root.val + left[0] + right[0];  // if current TreeNode is robbed, the maximum gain will only come from left and right child without robbery
        return res;
    }
}