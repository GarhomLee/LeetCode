https://leetcode.com/problems/nested-list-weight-sum/


// 思路：Recursion (DFS)
//         维护全局变量res，记录Integer值和层数level乘积的加和。
//         递归函数定义：void dfs(NestedInteger ni, int level)，表示处理在当前level的NestedInteger ni。
//         终止条件：NestedInteger为Integer而不是List，在res里加上Integer值和level的乘积，然后返回。
//         递归过程：对于包含List的NestedInteger，调用getList()得到展开的List，然后遍历List中的每个NestedInteger，
//             调用递归函数dfs()，同时传入level+1作为下一层的level。
// 时间复杂度：O(n), n=num of total integers
// 空间复杂度：O(d), d=depth of input NestedInteger
// follow-up：364. Nested List Weight Sum II

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    int res = 0;
    
    public int depthSum(List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            dfs(nestedList.get(i), 1);
        }
        
        return res;
    }
    
    private void dfs(NestedInteger ni, int level) {
        if (ni.isInteger()) {
            res += ni.getInteger() * level;
            return;
        }
        
        List<NestedInteger> list = ni.getList();
        for (int i = 0; i < list.size(); i++) {
            dfs(list.get(i), level + 1);
        }
    }
}