https://leetcode.com/problems/change-the-root-of-a-binary-tree/

idea: Brute Force
time complexity: O(h)
space complexity: O(1)

class Solution {
    public Node flipBinaryTree(Node root, Node leaf) {
        Node curr = leaf, next = curr.parent, nextParent = next.parent;
        while (curr != root) {
            if (curr.left != null) {
                curr.right = curr.left; // change curr's right
            }
            curr.left = next;   // change curr's left
            next.parent = curr; // change next's parent
            if (curr == next.left) {
                next.left = null;   // change next's left if necessary 
            }
            if (curr == next.right) {
                next.right = null;  // change next's right if necessary 
            }
            
            // update
            curr = next;
            next = nextParent;
            if (next != null) {
                nextParent = next.parent;
            }
        }
        leaf.parent = null; // change leaf's parent
        return leaf;
    }
}