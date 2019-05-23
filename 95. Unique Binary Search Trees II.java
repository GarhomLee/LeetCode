https://leetcode.com/problems/unique-binary-search-trees-ii/

// 总体思路：关于Tree的问题，可以考虑用recursion。
//         利用helper method，求取[start:end]的所有可能的树，然后返回包含这些树的所有root的List。
//         根据这个性质，对于[start:end]中的某个数作为root，可以用recursion求左子树和右子树，得到包含左右子树的root的两个List。
//         然后，将跟这个root有关的所有可能的左右子树的组合append到root后。最后返回的是所有可能的树的root的List。
// 犯错点：1.n==0时，属于特殊情况，直接返回空的List。否则，如果进入了helper method，返回的List不为空，而是长度为1，包含一个元素null。
//         2.在每次append左右子树之前，都要进行root的初始化，因为对于在List中加入元素，Java传的是reference。
// 注意：当到达终止条件start>end时，返回的是含有一个null元素的List，这样才能进入for loop然后在左子树或右子树为null时append null。

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();  // {Mistake 1: if n==0, the List should be empty, instead of a List of size 1 with a null}
        
        return generate(1, n);
    }
    
    /** helper method to get a list of roots which correspond to the tree including [start:end] */
    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> list = new ArrayList<>();

        /* termination condition */
        if (start > end) {
            list.add(null);  // Attention: null should be add into List, so that in the for loop it can be used. Otherwise, the List would be empty, and it would not enter the for loop.
            return list;
        }
        
        for (int i = start; i <= end; i++) {
            //TreeNode root = new TreeNode(i);  // {Mistake 2: wrong place to initialize TreeNode root. If it is initialized here, the roots with the same value will be the same root because they have same address, there for changing left and right child of one root will change all roots with the same address}
            List<TreeNode> leftList = generate(start, i - 1);  // get a List of all roots of left tree
            List<TreeNode> rightList = generate(i + 1, end);  // get a List of all roots of right tree
            
            /* append left and right child to the root i with all possible constructions */
            for (TreeNode leftChild: leftList) {
                for (TreeNode rightChild: rightList) {
                    TreeNode root = new TreeNode(i);  // {Correction 2: TreeNode root should be initialized right before appending left and right child, because Java pass the reference of an Object. Only in this way can the same root value with different tree be added into the List}
                    
                    root.left = leftChild;
                    root.right = rightChild;
                    list.add(root);  // even adding the same root value, we need a new root with different address
                }
            }
        }
        return list;
    }
}