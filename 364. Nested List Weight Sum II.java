https://leetcode.com/problems/nested-list-weight-sum-ii/

对比：339. Nested List Weight Sum

解法一：2-pass DFS
    先dfs找到max depth，再第二次dfs求结果。


解法二：BFS，参考：https://leetcode.com/problems/nested-list-weight-sum-ii/discuss/83655/JAVA-AC-BFS-solution
    根据公式化简，结果应该为(maxDepth + 1) * sumOfAllElements - sumOfElementTimesDepth

class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = 0, maxDepth = 0;
        int sumAll = 0, sumProduct = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni: nestedList) {
            queue.offer(ni);
        }
        
        while (!queue.isEmpty()) {
            depth++;
            maxDepth = Math.max(maxDepth, depth);
            int size = queue.size();
            while (size-- > 0) {
                NestedInteger curr = queue.poll();
                if (curr.isInteger()) {
                    sumAll += curr.getInteger();
                    sumProduct += curr.getInteger() * depth;
                } else {
                    for (NestedInteger next: curr.getList()) {
                        queue.offer(next);
                    }
                }
            }
        }
        
        return (maxDepth + 1) * sumAll - sumProduct;
    }
}


解法三：1-pass DFS
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

