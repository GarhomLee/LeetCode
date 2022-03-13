https://leetcode.com/problems/closest-binary-search-tree-value-ii/

idea: BST Inorder traversal + Two Pointers
    Do inorder traversal to flatten a BST, and record the minIdx with minDiff during traversal.
    Then starting from minIdx, compare diff on its left and right, and find the smaller ones iteratively.
time comp: O(n)
space comp: O(n)

class Solution {
    List<Integer> nums = new ArrayList<>();
    double minDiff = Double.MAX_VALUE;
    int minIdx = -1;
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        inorder(root, target);
        
        int n = nums.size();
        List<Integer> ret = new ArrayList<>();
        ret.add(nums.get(minIdx));
        int left = minIdx - 1, right = minIdx + 1;
        while (ret.size() < k) {
            if (left < 0) {
                ret.add(nums.get(right++));
            } else if (right >= n) {
                ret.add(nums.get(left--));
            } else {
                double leftDiff = Math.abs(target - nums.get(left)), rightDiff = Math.abs(nums.get(right) - target);
                if (leftDiff < rightDiff) {
                    ret.add(nums.get(left--));
                } else {
                    ret.add(nums.get(right++));
                }
            }
        }
        
        return ret;
    }
    
    private void inorder(TreeNode node, double target) {
        if (node == null) return;
        
        inorder(node.left, target);
        nums.add(node.val);
        if (Math.abs(node.val - target) < minDiff) {
            minDiff = Math.abs(node.val - target);
            minIdx = nums.size() - 1;
        }
        inorder(node.right, target);
    }
}