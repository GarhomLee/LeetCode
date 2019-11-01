https://leetcode.com/problems/binary-tree-vertical-order-traversal/

思路：BFS

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, root));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            map.putIfAbsent(curr.index, new ArrayList<>());
            map.get(curr.index).add(curr.treeNode.val);
            if (curr.treeNode.left != null) {
                queue.offer(new Node(curr.index - 1, curr.treeNode.left));
            }
            if (curr.treeNode.right != null) {
                queue.offer(new Node(curr.index + 1, curr.treeNode.right));
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (int key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
    
    class Node {
        int index;
        TreeNode treeNode;
        public Node(int i, TreeNode tn) {
            index = i;
            treeNode = tn;
        }
    }
}