https://leetcode.com/problems/factor-combinations/

// 思路：Backtracking (DFS)，不能简单套常规模版
//         递归函数定义：dfs(List<List<Integer>> res, List<Integer> list, int n, int start)，表示搜索
//             乘积为n，从start开始的所有因子组合。
//         终止条件：n <= 3，根据题意，因子不包括1和n本身，所以n <= 3时就可以停止搜索直接返回。
//         递归过程：三要素：Goal：得到跟当前n有关的【2个因子】
//                         Choices：从start开始的i，使得i ^ 2 <= n。
//                         Constraints：n % i == 0，可以得到2个因子i和n / i，且一定有i <= n / i，这样可以避免重复搜索。
//                 对于每一个n % i == 0的i，先将第一个因子i加入list，然后调用递归函数以i作为start搜索n / i。
//                 然后，将第二个因子n / i也加入list，这样就可以得到当前n有关的【2个因子】，因此将list加入res。
//                 在进行backtracking的时候，为了避免当前结果影响回溯后的其他结果，要将结果重置，因此将刚刚加入的两个因子
//                 n / i和i都从list中remove。
// 时间复杂度：???
// 空间复杂度：???

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        
        dfs(res, new ArrayList<>(), n, 2);
        
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> list, int n, int start) {
        if (n <= 3) {
            return;
        }
        
        for (int i = start; i * i <= n; i++) {
            if (n % i != 0) continue;
            list.add(i);  // factor 1
            
            dfs(res, list, n / i, i);
            list.add(n / i);  // factor 2, and there must be factor 2 >= factor 1
            res.add(new ArrayList<>(list));
            
            /* reset */
            list.remove(list.size() - 1);
            list.remove(list.size() - 1);
        }
    }
}