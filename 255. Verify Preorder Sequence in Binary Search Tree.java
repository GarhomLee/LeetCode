https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/

// 解法一：Recursion
//         递归函数定义：boolean dfs(int[] preorder, int start, int end, long lower)，
//             表示判断preorder[start:end]在给定的lower bound条件下是否能构成BST。
//         终止条件：1）start > end，范围内没有元素，返回true。
//                 2）preorder[start] < lower，不符合BST的preorder traversal结果，返回false。
//         递归过程：首先，根据preorder traversal性质，调用helper method找到从start位置开始的
//             比preorder[start]大的第一个数，将它的位置记为rightRootIndex。
//             然后，分别对可能的左子树preorder[start+1,rightRootIndex-1]和可能的右子树
//             preorder[rightRootIndex,end]调用递归得到结果，lower bound分别为lower和preorder[start]+1。
//             只有当左右子树都能构成BST，当前范围才能构成BST。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：对于右子树的范围，start位置就是rightRootIndex，而不是rightRootIndex + 1。

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1, Integer.MIN_VALUE);
    }
    
    private boolean dfs(int[] preorder, int start, int end, long lower) {
        if (start > end) {
            return true;
        }
        if (preorder[start] < lower) {
            return false;
        }
        
        int rightRootIndex = find(preorder, start, end);
        
        return dfs(preorder, start + 1, rightRootIndex - 1, lower)
            //&& dfs(preorder, rightRootIndex + 1, end, preorder[start] + 1);  // {Mistake 1}
            && dfs(preorder, rightRootIndex, end, preorder[start] + 1);  // {Correction 1}
    }
    
    private int find(int[] preorder, int left, int right) {
        int index = left + 1;
        while (index <= right && preorder[index] <= preorder[left]) {
            index++;
        }
        
        return index;
    }
}


解法二：Iteration with two Stacks，参考：https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space
时间复杂度：O(n)
空间复杂度：O(n)

public boolean verifyPreorder(int[] preorder) {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> inorder = new Stack<>();
    
    for(int v : preorder){
        if(!inorder.isEmpty() && v < inorder.peek())
            return false;
        while(!stack.isEmpty() && v > stack.peek()){
            inorder.push(stack.pop());
        }
        stack.push(v);
    }
    return true;
}

优化：空间复杂度降到O(1)，参考：https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space

public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE, i = -1;
    for (int p : preorder) {
        if (p < low)
            return false;
        while (i >= 0 && p > preorder[i])
            low = preorder[i--];
        preorder[++i] = p;
    }
    return true;
}