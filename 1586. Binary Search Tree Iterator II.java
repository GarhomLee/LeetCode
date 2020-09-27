https://leetcode.com/problems/binary-search-tree-iterator-ii/

idea: inorder traversal
time complexity: O(1)
space complexity: O(n)

class BSTIterator {
    List<Integer> list;
    int idx;
    
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
        idx = -1;
    }
    
    private void dfs(TreeNode node) {
        if (node == null) return;
        
        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }
    
    public boolean hasNext() {
        return idx + 1 < list.size();
    }
    
    public int next() {
        idx++;
        return list.get(idx);
    }
    
    public boolean hasPrev() {
        return idx - 1 >= 0;
    }
    
    public int prev() {
        idx--;
        return list.get(idx);
    }
}
