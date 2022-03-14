https://leetcode.com/problems/find-leaves-of-binary-tree/

// 思路：DFS
//         观察可知，假设以所有初始leaf node为height==0，那么height of internal node就是左右子树最大height+1。注意这个height和以root为基准
//         得到的height是不同的，这里不能考虑以root为基准的height。
//         巧妙转化为将所有节点根据height分组，那么就转化为dfs求height的问题。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    private int dfs(TreeNode node, List<List<Integer>> res) {
        if (node == null) {
            return -1;
        }
        
        int left = dfs(node.left, res);
        int right = dfs(node.right, res);
        int height = 1 + Math.max(left, right);
        if (res.size() == height) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(node.val);
        return height;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
}


二刷（麻烦版）：根据child->parent建图，转常规bfs

class Solution {
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    Map<TreeNode, Integer> outdegree = new HashMap<>();
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        buildMap(root);    
        
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (TreeNode node: outdegree.keySet()) {
            if (outdegree.get(node) == 0) {
                queue.offer(node);
                list.add(node.val);
            }
        }
        ret.add(list);
        
        while (!queue.isEmpty()) {
            List<Integer> nodeList = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                if (curr == root) break;  // root
                
                TreeNode parent = parentMap.get(curr);
                outdegree.put(parent, outdegree.get(parent) - 1);
                if (outdegree.get(parent) == 0) {
                    queue.offer(parent);
                    nodeList.add(parent.val);
                }
            }
            
            if (!nodeList.isEmpty()) ret.add(nodeList);
        }
        
        return ret;
    }
    
    private void buildMap(TreeNode node) {
        if (node == null) return;
        
        if (node.left != null) {
            parentMap.put(node.left, node);
            outdegree.put(node, outdegree.getOrDefault(node, 0) + 1);
            buildMap(node.left);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            outdegree.put(node, outdegree.getOrDefault(node, 0) + 1);
            buildMap(node.right);
        }        
        
        if (node.left == null && node.right == null) {
            // left node
            outdegree.put(node, 0);
        }
    }
}