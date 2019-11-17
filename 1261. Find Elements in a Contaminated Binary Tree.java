https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/

// 思路：DFS + Hash Table
//         在DFS重构树的同时，将出现过的元素加入HashSet，因此find()可以直接返回这个元素是否存在。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.思路错误：这里不是BST，在find()时不能利用当前node.val和target的大小关系来判断是否只搜索某一个子树。
//             这题树的结构类似heap。

class FindElements {
    TreeNode root;
    Set<Integer> set;
    
    private void dfs(TreeNode node, int val) {
        if (node == null) return;
        
        node.val = val;
        set.add(val);
        dfs(node.left, 2 * val + 1);
        dfs(node.right, 2 * val + 2);
    }
    
    
    public FindElements(TreeNode r) {
        root = r;
        set = new HashSet<>();
        dfs(root, 0);
    }
    
    public boolean find(int target) {
        return set.contains(target);
    }
}
