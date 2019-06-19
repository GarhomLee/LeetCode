https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

// 思路：Recursion
//      根据BST性质，可以使用nums[low:high]的中间元素mid作为树的根，然后左子树范围为[low:mid-1]，右子树范围为[mid+1:high]。
//      递归得到左右子树，返回root。
// 时间复杂度：O(n)
// 空间复杂度：O(log n)

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return convert(nums, 0, nums.length - 1);
    }
    
    private TreeNode convert(int[] nums, int low, int high) {
        /* corner cases */
        if (low > high) return null;
        if (low == high) return new TreeNode(nums[low]);
        
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);  // use mid element as the root
        root.left = convert(nums, low, mid - 1);  // use recursion to get left child tree
        root.right = convert(nums, mid + 1, high);  // use recursion to get right child tree
        return root;
    }
}