https://leetcode.com/problems/most-frequent-subtree-sum/

// 思路：Recursion (DFS) + HashMap
//      理解题目的subtree指的是以当前root为根的整个数，包括所有左子树和右子树节点。
//      HashMap的key为出现过的sum，value为出现的频率。
//      递归求解当前subtree的sum，更新HashMap和全局变量maxCount。
//      求解完所有subtree后，遍历HashMap，找到频率等于maxCount的所有sum，加入List，再转成int[]返回之。
// 注意：不能用list.toArray(int[0])，因为只能转成Integer[]，而Integer[]不能变成题目要求的int[]

class Solution {
    List<Integer> list = new ArrayList();
    Map<Integer, Integer> map = new HashMap();
    int maxCount = 0;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        /* corner case */
        if (root == null) return new int[0];
        /* dfs */
        dfs(root);
        /* add all sums with frequency of maxCount into List */
        for (int sum: map.keySet()) {
            if (map.get(sum) == maxCount) list.add(sum);
        }
        /* transform an Integer List to int array */
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
    
    private int dfs(TreeNode root) {
        /* termination condition */
        if (root == null) return 0;
        /* recursively get the sum of current subtree */
        int sum = root.val + dfs(root.left) + dfs(root.right);
        /* update HashMap and global variable maxCount */
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        maxCount = Math.max(maxCount, map.get(sum));

        return sum;
    }
}