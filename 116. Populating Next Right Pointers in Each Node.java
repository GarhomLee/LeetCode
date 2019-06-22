https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

// 解法一：Recursion
//        将当前root作为parent，先连接了root.left.next，利用root.next连接root.right.next，再递归更新root.left和root.right。
// 注意：要先将root.left和root.right的next设置好了，才能进行递归操作，否则root.next还是为null

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left == null) return root;
        
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        
        root.left = connect(root.left);
        root.right = connect(root.right);
        return root;
    }
}


// 解法二：Iteration with Queue
//        level order traversal模版改写。

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        
        Queue<Node> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node curr = queue.poll();
                if (curr.left == null) break;
                curr.left.next = curr.right;
                if (curr.next != null) curr.right.next = curr.next.left;
                queue.offer(curr.left);  // curr.left is now guaranteed to be non-null
                queue.offer(curr.right);  // curr.right is now guaranteed to be non-null
            }
        }
        return root;
    }
}