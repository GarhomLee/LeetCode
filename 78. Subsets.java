https://leetcode.com/problems/subsets/

// 总体思路：DFS+Backtracking模版。需要注意goal的选取。
//         goal:每加入一个元素即为新的subset，记录每一个新subset
//         choices:从start开始，到nums数组最后一个元素
//         constraints:不能走回头路，选取之前的元素
// 犯错点：1.goal应该为每加入一个元素即为新的subset，记录每一个新subset，而不是扫描完所有nums数组元素后的subset
//        2.empty subset需要特殊处理

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());  // {Mistake 2} {Correction 2: an empty subset is a special case that will not be processed in helper method}
        
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> list) {
        if (start == nums.length) {
            //res.add(new ArrayList<Integer>(list));  // {Mistake 1}
            return;
        }
        
        /* choices */
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            /* goal */
            res.add(new ArrayList<Integer>(list));  // {Correction 1: the goal is every possible subset, not subset reaching the end of nums array}
            
            dfs(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}