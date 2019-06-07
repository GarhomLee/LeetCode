https://leetcode.com/problems/combinations/

// 总体思路：DFS+Backtracking模版。
//         goal: k==0，combination里的数字数量已满
//         choices:从start开始的数
//         constraint:要有足够的数字填充下一层，所以i <= n - k + 1

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> combine(int n, int k) {
        dfs(n, 1, k, new ArrayList<Integer>());
        return res;
    }
    
    private void dfs(int n, int start, int k, List<Integer> list) {
        /* goal */
        if (k == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        /* choices */
        for (int i = start; i <= n - k + 1; i++) {  // constraints
            list.add(i);
            dfs(n, i + 1, k - 1, list);
            list.remove(list.size() - 1);
        }
    }
}