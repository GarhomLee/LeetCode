https://leetcode.com/problems/range-sum-of-bst/

// 解法一：Morris Traversal模版，使用preorder traversal的形式
// 时间复杂度：O(N)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：对于每个TreeNode实际操作的函数，是在找完predecessor后，和在curr.left为空的时候，
//             而不能在每个curr!=null的时候，因为如果curr来自于pre.right，那么在前序遍历的过程中实际上curr
//             已经遍历过了，会造成重复计算。

class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;
        TreeNode curr = root;
        while (curr != null) {
            /* if (curr.val > R) {
                break;
            } else if (curr.val >= L) {
                sum += curr.val;
            }*/  // {Mistake 1}

            if (curr.left != null) {
                TreeNode pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == curr) {
                    pre.right = null;
                    /* actual function */
                    if (curr.val > R) {
                        break;
                    } else if (curr.val >= L) {
                        sum += curr.val;
                    }  // {Correction 1}
                    /* move curr to next node */
                    curr = curr.right;
                } else {
                    pre.right = curr;
                    /* move curr to next node */
                    curr = curr.left;
                }
            } else {
                /* actual function */
                if (curr.val > R) {
                    break;
                } else if (curr.val >= L) {
                    sum += curr.val;
                }  // {Correction 1}
                /* move curr to next node */
                curr = curr.right;
            }
        }
        return sum;
    }
}