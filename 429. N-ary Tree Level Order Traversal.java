https://leetcode.com/problems/n-ary-tree-level-order-traversal/

// 解法一：BFS Iteration with Queue
//        套用模版。

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList();
        Queue<Node> queue = new LinkedList();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList();
            while (size-- > 0) {
                Node curr = queue.poll();
                list.add(curr.val);
                for (Node child: curr.children) {
                    queue.offer(child);
                }
            }
            res.add(list);
        }
        return res;
    }
}
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/