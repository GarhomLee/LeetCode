https://leetcode.com/problems/path-sum-iii/

// 思路：Recursion。113. Path Sum II的follow-up。条件放宽为可以从任意节点开始，到任意节点结束。
//      所以，以当前root为根的树的path sum由三部分组成：sum从当前root开始计算的路径数量，以root.left为根、sum为
//      目标的树的路径数量，以及以root.right为根、sum为目标的树的路径数量。
//      后两个可以通过递归调用求得，第一个则需要利用helper method。
//      在helper method，root.val == sum时表明有一条新路径，标记为1，否则为0。要注意的是，即使root.val == sum，
//      仍然需要在root.left找sum - root.val和在root.right找sum - root.val的路径数，因为可能出现sum == -1，
//      path = [1,-2,1,-1]这样后面的节点互相抵消的情况。

class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathFromCurr(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathFromCurr(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathFromCurr(root.left, sum - root.val) + pathFromCurr(root.right, sum - root.val) + (root.val == sum ? 1 : 0);
    }
}