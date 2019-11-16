https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

// 解法一：Iteration with Stack (Inorder Travesal)
//         维护变量head指向double linked list的头部，变量pre指向inorder traversal中上一次遍历到的Node。
//         每次只需要更新当前Node curr的左指针left和pre的右指针right。最后更新head的左指针和pre的右指针，
//         此时pre指向了double linked list的尾部。
// 时间复杂度：O(n)
// 空间复杂度：O(h) or O(n)

class Solution {
    public Node treeToDoublyList(Node root) {
        Node head = null, pre = null;
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            if (head == null) {
                head = curr;
            } else if (pre != null) {
                curr.left = pre;
                pre.right = curr;
            }
            
            pre = curr;
            curr = curr.right;
        }
        
        if (head != null && pre != null) {
            head.left = pre;
            pre.right = head;
        }
        
        return head;
    }
}


解法二：Recursion (DFS, Inorder Travesal)