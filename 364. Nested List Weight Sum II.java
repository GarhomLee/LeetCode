https://leetcode.com/problems/nested-list-weight-sum-ii/

对比：339. Nested List Weight Sum

解法一：DFS

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    List<List<Integer>> levels = new ArrayList<>();
    
    private void dfs(NestedInteger curr, int level) {
        if (level == levels.size()) {
            levels.add(new ArrayList<>());
        }
        List<NestedInteger> list = curr.getList();
        for (NestedInteger next : list) {
            if (next.isInteger()) {
                levels.get(level).add(next.getInteger());
            } else {
                dfs(next, level + 1);
            }
        }
    }
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        NestedInteger root = new NestedInteger();
        for (NestedInteger ni : nestedList) {
            root.add(ni);
        }
        dfs(root, 0);
        
        int res = 0, size = levels.size();
        for (int i = 0; i < size; i++) {
            int sum = 0;
            List<Integer> list = levels.get(i);
            for (int num : list) {
                sum += num;
            }
            res += sum * (size - i);
        }
        
        return res;
    }
}


解法二：BFS，参考：https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83655/JAVA-AC-BFS-solution