https://leetcode.com/problems/permutations/

// 总体思路：DFS+Backtracking模版。
//         goal: count == nums.length，所有nums数组里的数字都用完了
//         choices:所有nums数组里的数字
//         constraint:在上一层已经用过的数字不能再用，即维护used数组来判断该数字是否已经用过
// 犯错点：1.不能使用Set，因为Set不能在遍历迭代的同时进行元素删除操作
//        2.count更新不能写成count++，否则即使没有进入下一层也会更新，造成错误
//        3.used[i]最后要reset

class Solution {
    List<List<Integer>> res = new ArrayList();
    //Set<Integer> candidateSet = new HashSet();  // {Mistake 1: it is not allow to removing elements from Set while iterating it}
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];  // {Correction 1}
        dfs(nums, used, new ArrayList<Integer>(), 0);
        return res;
    }
    
    private void dfs(int[] nums, boolean[] used, List<Integer> list, int count) {
        if (count == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            
            used[i] = true;
            list.add(nums[i]);
            //dfs(nums, used, list, count++);  // {Mistake 2: if use ++ operation, count will increase even it is not going into the next level}  
            dfs(nums, used, list, count + 1);  // {Correction 2}
            list.remove(list.size() - 1);
            used[i] = false;  // {Mistake 3} {Correction 3: reset}
        }
    }
}