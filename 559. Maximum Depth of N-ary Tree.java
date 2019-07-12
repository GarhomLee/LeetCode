https://leetcode.com/problems/maximum-depth-of-n-ary-tree/

// 解法一：Recursion + DFS
//        Binary Tree找最大高度的模版的变化版本，子树从0-2个变为0-N个。

class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 1;
        for (Node child: root.children) {
            depth = Math.max(depth, 1 + maxDepth(child));
        }
        return depth;
    }
}


// 解法二：Iteration + level order traversal (BFS)
// 犯错点：1.数据结构知识点错误：要记得先从Queue中取出元素，记为curr
//        2.题目理解错误：不是Binary Tree了，是N-ary Tree，子树子树从0-2个变为0-N个。

class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList();
        if (root != null) queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size-- > 0) {
                // {Mistake 1}
                Node curr = queue.poll();  // {Correction 1}
                //if (curr.left != null) queue.offer(curr.left);  // {Mistake 2}
                //if (curr.right != null) queue.offer(curr.right);  // {Mistake 2}
                for (Node child: curr.children) {  // {Correction 2}
                    queue.offer(child);
                }
            }
        }
        return depth;
    }
}