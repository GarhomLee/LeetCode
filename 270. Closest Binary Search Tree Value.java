https://leetcode.com/problems/closest-binary-search-tree-value/

// 思路：BST Binary Search
//         维护变量：res，表示目前找到的结果；diff，表示当前res和target的差值，为正数，是当前能找到的全局最小差值。
//         对于当前节点curr，有3种可能：
//         1）curr.val == target，说明diff一定为0，直接返回curr.val
//         2）target比curr.val小，那么target可能会出现在左子树。判断diff和res是否要更新，然后更新curr为curr.left。
//         3）target比curr.val大，那么target可能会出现在右子树。判断diff和res是否要更新，然后更新curr为curr.right。
// 时间复杂度：O(h), h=height of BST
// 空间复杂度：O(1)
// 犯错点：1.数据溢出：由于target是double类型，可以给到比Integer.MAX_VALUE还大，那么求target和root.val差值的时候
//             会导致数据溢出。

class Solution {
    public int closestValue(TreeNode root, double target) {
        //int smaller = Integer.MIN_VALUE, greater = Integer.MAX_VALUE;  // {Mistake 1}
        double diff = Double.MAX_VALUE;  // {Correction 1}
        int res = root.val;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == target) {
                return curr.val;
            } else if (curr.val > target) {
                if ((double) curr.val - target < diff) {
                    diff = (double) curr.val - target;
                    res = curr.val;
                }
                curr = curr.left;
            } else {
                if (target - (double) curr.val < diff) {
                    diff = target - (double) curr.val;
                    res = curr.val;
                }
                curr = curr.right;
            }
        }
        return res;
    }
}