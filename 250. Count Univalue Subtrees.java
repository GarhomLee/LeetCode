https://leetcode.com/problems/count-univalue-subtrees/

// 思路：DFS，类似postorder travesal
//         在递归的过程中，不断更新全局变量count，记录univalue subtree的数量。
//         递归函数定义：boolean dfs(TreeNode curr)，表示检查curr节点为根的树是否为univalue。
//         终止条件：curr == null，返回true。
//         递归过程：维护临时变量isUni，初始化为true。
//                 先递归遍历左子树，利用递归结果及curr.left!=null时curr.left是否和curr相等来
//                 更新isUni。
//                 然后递归遍历左子树，利用递归结果及curr.right!=null时curr.right是否和curr相等来
//                 更新isUni。
//                 最后再对curr进行操作。如果此时isUni为false，那么说明以curr为根的树不是univalue，
//                 返回false。否则，找到了一个univalue subtree，更新count++，同时返回true。因此，
//                 是postorder travesal。
// 时间复杂度：O(n), n=num of tree nodes
// 空间复杂度：O(h), h=height of tree
// 犯错点：1.细节错误：由于逻辑判断操作从左往右执行，如果"&&"中某一个逻辑判断结果为false，那么后续
//             的判断都不会再进行。因此，recursion的代码要放在逻辑判断的最前面，不能放中间，否则
//             可能不会执行recursion。

class Solution {
    int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return count;
    }
    
    private boolean dfs(TreeNode curr) {
        if (curr == null) {
            return true;
        }
        
        boolean isUni = true;
        
        //isUni = isUni && dfs(curr.left) && (curr.left == null ? true : curr.val == curr.left.val);
        //isUni = isUni && dfs(curr.right) && (curr.right == null ? true : curr.val == curr.right.val);  // {Mistake 1}
        isUni = dfs(curr.left) && isUni && (curr.left == null ? true : curr.val == curr.left.val);
        isUni = dfs(curr.right) && isUni && (curr.right == null ? true : curr.val == curr.right.val);  // {Correction 1}
        
        if (!isUni) {
            return false;
        }
        
        count++;
        return true;
    }
}