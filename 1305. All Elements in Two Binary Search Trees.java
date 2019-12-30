https://leetcode.com/problems/all-elements-in-two-binary-search-trees/

思路：DFS，in order travesal + merge sorted list
时间复杂度：O(n)
空间复杂度：O(n)
优化？

class Solution {
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        
        int i = 0, j = 0;
        while (i < list1.size() || j < list2.size()) {
            if (i == list1.size()) {
                res.add(list2.get(j++));
            } else if (j == list2.size()) {
                res.add(list1.get(i++));
            } else if (list1.get(i) < list2.get(j)) {
                res.add(list1.get(i++));
            } else {
                res.add(list2.get(j++));
            }
        }
        
        return res;
    }
}