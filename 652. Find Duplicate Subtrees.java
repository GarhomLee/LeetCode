https://leetcode.com/problems/find-duplicate-subtrees/

思路：DFS + Hash Map

时间复杂度：O(n^2) for storing all Strings
空间复杂度：O(n^2) for storing all Strings
犯错点：1.思路错误：只用HashSet无法区分重复出现的子树是否已经被记录在res里，因此需要用HashMap进行计数。

class Solution {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();
    
    private String dfs(TreeNode node) {
        if (node == null) {
            return "#";
        }
        
        String left = dfs(node.left);
        String right = dfs(node.right);
        if (!left.equals("#")) {
            if (map.containsKey(left) && map.get(left) == 1) {
                res.add(node.left);
            } 
            
            map.put(left, map.getOrDefault(left, 0) + 1);
            
        }
        
        if (!right.equals("#")) {
            if (map.containsKey(right) && map.get(right) == 1) {
                res.add(node.right);
            } 
            
            map.put(right, map.getOrDefault(right, 0) + 1);
            
        }
        
        return node.val + left + right;
    }
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        String order = dfs(root);
        if (!order.equals("#") && map.containsKey(order) && map.get(order) == 1) {
            res.add(root);
        }
        
        return res;
    }
}