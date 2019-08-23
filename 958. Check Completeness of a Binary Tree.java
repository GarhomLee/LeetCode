https://leetcode.com/problems/check-completeness-of-a-binary-tree/

// 思路：BFS
//         根据completeness的性质，所有节点必须排满，最后一层也要从左向右排满。因此，可以记录null node出现的情况来进行判断。
//         step0: 特殊情况，root==null，直接返回true。
//         step1: 维护变量isEnd，表示是否从左向右排满，初始化为false。如果isEnd==true，那么说明这一层出现了null node。
//         step2: 利用Queue实现BFS每层遍历。由于Queue中允许null node存在，因此有两种情况：
//                 1）当前poll出的节点为null，那么更新isEnd为true
//                 2）当前poll出的节点不为null。如果isEnd已经为true，那么不符合completeness的定义，返回false。
//                     否则，将root.left和root.right都放入Queue中，不区分null node。
//         step3: 遍历结束，都没有返回false，说明是一棵完全树，返回true。
// 时间复杂度：O(n) or O(n + 2^h), since there will be 2^h = n + 1 null nodes if it is complete
// 空间复杂度：O(n) or O(n + 2^h), since there will be 2^h = n + 1 null nodes if it is complete

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isEnd = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                isEnd = true;
            } else {
                if (isEnd) {
                    return false;
                }
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        
        return true;
    }
}