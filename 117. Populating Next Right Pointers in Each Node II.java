https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

// 思路：Iteration with pointer
//      维护一个dummy节点，它的next指向下一层的最左边的节点。
//      维护一个pre节点，用来连接下一层的next，同时也用pre节点来实现dummy.next指向下一层最左边节点。
//      当curr.left != null时，curr.left就可以被pre.next连接，同时pre更新为pre.next。curr.right != null同理。
//      然后移动curr。如果curr.next == null，说明当前层已经遍历完，而且下一层已经连接完，那么curr更新为dummy.next，
//      同时dummy.next重置为null，等待pre帮助其进行连接。

class Solution {
    public Node connect(Node root) {        
        Node dummy = new Node(-1, null, null, null);
        Node curr = root, pre = dummy;
        while (curr != null) {
            if (curr.left != null) {
                pre.next = curr.left;
                pre = pre.next;
            }
            if (curr.right != null) {
                pre.next = curr.right;
                pre = pre.next;
            }
            
            if (curr.next != null) {  // move parent pointer to its next
                curr = curr.next;
            } else {  // parent pointer has no next, set curr to the leftmost node of next level
                curr = dummy.next;
                dummy.next = null;
                pre = dummy;
            }
        }
        return root;
    }
}