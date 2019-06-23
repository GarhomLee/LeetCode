https://leetcode.com/problems/recover-binary-search-tree/

// 视频讲解：https://www.youtube.com/watch?v=QZMropFflv4
// 思路：Morris traversal
//      利用BST的inorder traversal能得到sorted array的性质，先进行inorder traversal，找到要调换的两个node，然后调换之。
//      题目要求要用O(1)空间，所以可以用Morris traversal。Morris traversal总体分为两步：1）连接curr.left的最右节点到curr节点，以便进行
//      inorder traversal；2）进行inorder traversal。
//      因此，当curr != null时，在循环中进行以下步骤：
//      1）curr.left != null，利用while循环找到curr.left的最右节点。循环跳出可能有两种情况：
//         a）temp.right == null，这时需要建立连接temp.right = curr，然后curr更新指向curr.left
//         b）temp.right == curr，说明已经连接已经存在，且curr.left为根的子树已经被遍历过了，轮到当前根curr。
//             所以先取消连接，然后进行curr有关的操作，在这里就是判断是否找到了first和second。然后curr更新指向curr.right，表示接下来要遍历右子树。
//      2）curr.left == null，说明左子树不需要遍历，直接进行当前根curr有关的操作，和b）代码完全一样。然后curr更新指向curr.right，表示接下来要遍历右子树。
//      为了找到first和second，需要维护pre指针，利用pre.val和curr.val的大小关系来找到需要调换的两个node。
// 犯错点：1.在build link和destroy link的步骤中，如果temp.right == curr，说明左子树已经遍历过了，需要destroy link，所以temp.right要重新赋值为null
//        2.first指向sorted array里异常的由大变小的较大的那个node，对应的是pre；
//          second指向sorted array里异常的由大变小的较小的那个node，对应的是curr
//        3.异常的由大变小的node pair可能有两对，也可能只有一对，所以不能用if else来判断，应该用两个if来分别判断是否要对first和second赋值，来应对只有一对的情况。

class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null, pre = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) temp = temp.right;
                if (temp.right == null) {  // build the link to the next node in inorder traversal
                    temp.right = curr;
                    curr = curr.left;
                } else if (temp.right == curr) {  // destroy the link, and the left child tree has been visited, so visit current node and then go right
                    // {Mistake 1}
                    temp.right = null;  // {Correction 1: destroy the link, otherwise it will be in a infinite loop and cause memory limit exceed}
                    if (pre != null && pre.val > curr.val) {
                    if (first == null) first = pre;
                    //else second = pre;  // {Mistake 2}
                    //else second = curr;  // {Mistake 2}
                    if (first != null) second = curr;  // {Correction 2}
                pre = curr;
                curr = curr.right;
                }
            } else {  // left child tree is null, so visit current node and then go right
                if (pre != null && pre.val > curr.val) {
                    if (first == null) first = pre;
                    //else second = pre;  // {Mistake 2}
                    //else second = curr;  // {Mistake 3}
                    if (first != null) second = curr;  // {Correction 2: the second value to be swapped should be the smaller one} {Correction 3}
                pre = curr;
                curr = curr.right;
            }
        }
        
        /* swap two values which was swapped by mistake */
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}