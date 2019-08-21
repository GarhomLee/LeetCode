https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

// 解法一：Preorder构造树模版 + Binary Search，对比105. Construct Binary Tree from Preorder and Inorder Traversal
//         给定当前树的范围preorder[start:end]，已知root为preorder[start]，可以利用binary search找到比root大的最小数位置
//         作为右子树起始位置，这样就能划分出左子树和右子树的范围。
//         递归构造整棵树。
// 时间复杂度：O(n log n)
// 空间复杂度：O(log n)

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        int val = preorder[start];
        int rightStart = binarysearch(preorder, start + 1, end, preorder[start]);
        TreeNode root = new TreeNode(val);
        root.left = build(preorder, start + 1, rightStart - 1);
        root.right = build(preorder, rightStart, end);
        return root;
    }
    
    private int binarysearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}


解法二：利用Preorder Travsersal的特性优化时间复杂度，详见：https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/discuss/252232/JavaC%2B%2BPython-O(N)-Solution
时间复杂度：O(n)
空间复杂度：O(log n)