https://leetcode.com/problems/subsets-ii/

// 总体思路：DFS+Backtracking模版。注意duplicate的干扰，需要先将nums数组排序，然后才能排除duplicates。
//         goal:每加入一个元素即为新的subset，记录每一个新subset
//         choices:从start开始，到nums数组最后一个元素（除了重复元素）
//         constraints:1）不能走回头路，选取之前的元素
//                     2）从start开始的第二个元素开始，不能搜索和前一个元素相同的重复元素
// 犯错点：1.nums数组必须先排好序，才能利用相邻两个元素是否相同来避免重复搜索

class Solution {
    List<List<Integer>> res = new ArrayList();    
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);  // {Mistake 1} {Correction 1: need to sort before going into dfs}
        
        dfs(nums, 0, new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());
        
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> list) {
        if (start == nums.length) return;
        
        /* choices */
        for (int i = start; i < nums.length; i++) {
            /* constraints */
            if (i > start && nums[i] == nums[i - 1]) continue;  // avoid duplicates
                                                                // {requirement: sorted array}
            list.add(nums[i]);
            /* goal */
            res.add(new ArrayList<Integer>(list));
            dfs(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}