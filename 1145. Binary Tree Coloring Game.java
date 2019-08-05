https://leetcode.com/problems/binary-tree-coloring-game/

// 思路：Tree + DFS
//         观察题目可以发现，当player1选定了第一个节点x，player2如果要赢，有且只有一种情况：以节点x为virtual root，
//         从3个孩子节点（即原左孩子，原右孩子，和原父节点）中选对应的子树中node个数（即size）最大的那个子树。实际上，
//         这个子树的size就是player2能得到的所有node。
//         维护全局变量：leftSize，表示节点x左子树的size；rightSize，表示节点x右子树的size。
//         调用递归函数int getSize(TreeNode root, int x)，同时完成两件事：1）搜索节点x；2）得到节点x左右子树的size。
//         因此，父节点对应的子树的size就是n - leftSize - rightSize - 1。
//         结果返回判断三个子树的最大size是否比另两个子树size之和大即可。
// 时间复杂度：O(n)
// 空间复杂度：O(log n)
// 犯错点：1.数据样例错误：数据样例不仅限于有序的满二叉树，而是任意结构的[1:n]的二叉树。


class Solution {
    int leftSize = 0;
    int rightSize = 0;
    
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        getSize(root, x);
        
        int remaining = n - leftSize - rightSize - 1;
        int maxSize = Math.max(remaining, Math.max(leftSize, rightSize));
        return maxSize > n - maxSize - 1;
    }
    
    private int getSize(TreeNode root, int x) {
        if (root == null) return 0;
        int left = getSize(root.left, x), right = getSize(root.right, x);

        if (root.val == x) {
            leftSize = left;
            rightSize = right;
        }

        return 1 + left + right;
    }
}