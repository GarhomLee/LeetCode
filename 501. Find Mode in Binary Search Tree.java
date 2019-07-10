https://leetcode.com/problems/find-mode-in-binary-search-tree/

// 思路：Recursion + Inorder Traversal
//      维护全局变量：count，表示当前元素的个数；maxCount，表示众数元素的个数；pre，表示当前节点在中序遍历的前一个节点。
//      递归函数定义：inorder(TreeNode root)，中序遍历以root为根的数
//      终止条件：root == null
//      递归过程：先调用递归函数遍历左子树root.left，然后对root进行操作。
//              先根据root.val和pre.val判断count的更新方式。
//              然后比较count和maxCount：
//              1）如果count == maxCount，当前元素的个数也达到众数，加入list
//              2）如果count > maxCount，表示当前元素的个数是新的众数，之前认为是众数的元素都要从list清空
//              3）如果count < maxCount，忽略之
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.思路错误：count和maxCount的更新不能等到root.val != pre.val，否则如果遇到最后几个都是连续的相同元素，
//          因为一直是root.val == pre.val，所以直到循环结束都没法更新count和maxCount。
//          因此，需要在遍历右子树前，就要根据root.val更新count和maxCount。

class Solution {
    List<Integer> list = new ArrayList();
    int count = 0;
    int maxCount = 0;
    TreeNode pre = null;
    
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        inorder(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        
        inorder(root.left);
        
        if (pre != null && root.val != pre.val) {
            count = 1;
        } else {
            count++;
        }
        
        if (count == maxCount) {
            list.add(root.val);
        } else if (count > maxCount) {
            list.clear();
            list.add(root.val);
            maxCount = count;
        }
        
        pre = root;
        inorder(root.right);
        
    }
}

// follow-up: O(1) space
// 思路：Two-pass Inorder Traversal，见https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98101/Proper-O(1)-space
//      第一次遍历是为了找mode count，然后根据mode count创建数组，第二次遍历再次count每个元素，把个数等于
//      mode count的元素依次放进数组。实际上，两次遍历可以用同一个helper method代码。