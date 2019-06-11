https://leetcode.com/problems/binary-tree-postorder-traversal/

// 解法一：Recursion

class Solution {
    List<Integer> res = new ArrayList();

    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return res;
    }
    
    private void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        res.add(node.val);
    }
}


// 解法二：Iteration as Reversing Preorder Traversal
//        完全和Preorder Traversal对称：
//        1）将curr.val加在List头部
//        2）先走右边，不断加入right child
//        3）遇到null，将stack顶部元素pop出，curr=curr.left，然后进入下一轮循环

class Solution {
    LinkedList<Integer> res = new LinkedList();

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                res.addFirst(curr.val);
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            curr = curr.left;
        }
        
        return res;
    }
}

// 解法三：Iteration Without Reversing Preorder Traversal，关键点在于【判断right child有没有被访问】。
//        同样先走左边，不断加入left child。
//        如果遇到left child为null，pop出一个元素，开始走右边。
//        走之前，【先做一个判断：如果right child不为null（即当前curr不为leave node），且right child node没有访问过，那么将curr【放回Stack】，curr更新为curr.right。
//        如果right child为null（即当前curr为leave node），或者right child node已经访问过了，那么curr就是最后访问的，符合postorder traversal定义。
//        所以将curr.val加入List，然后pre=curr表示下一次判断可以知道curr刚刚被访问过，同时curr更新为null可以跳过while loop直接从Stack取顶部元素。

class Solution {
    List<Integer> res = new ArrayList();

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root, pre = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            if (curr.right != null && curr.right != pre) {  // curr has right child and the right child is not visited
                stack.push(curr);
                curr = curr.right;
                continue;
            }
            res.add(curr.val);
            pre = curr;  // mark this TreeNode as is visited
            curr = null;  // in order to skip next while loop and pop the top from Stack directly
        }
        
        return res;
    }
}