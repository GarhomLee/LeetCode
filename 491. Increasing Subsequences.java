https://leetcode.com/problems/increasing-subsequences/

// 对比：和473. Matchsticks to Square的去重复方法的不同之处。

// 思路：DFS (Recursion) + Backtracking
//         调用递归函数dfs(int[] nums, int start, int preNum, List<Integer> list)得到从start位置开始的
//         比preNum大的数组成的increasing subsequences。
//         goal：任何个数>=2的subsequences都要加入res列表
//         choices：从start位置开始到nums数组最后一个元素
//         constraints：1）元素不能比当前列表最后一个元素（即preNum）小
//                     2）当前层不能搜索重复元素，由于不能排序，所以要用HashSet去重复
// 犯错点：1.重复元素错误：由于nums数组没有排序，因此只靠判断相邻两个元素是否相等，无法排除相同元素分隔很远的情况。
//             因此，可以使用HashSet，记录当前层使用过的元素，从而跳过已经使用的元素。

class Solution {
    List<List<Integer>> res = new ArrayList();
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums.length < 2) return res;
        dfs(nums, 0, Integer.MIN_VALUE, new ArrayList()); 
        return res;
    }
    
    private void dfs(int[] nums, int start, int preNum, List<Integer> list) {
        /* GOAL */
        if (list.size() >= 2) res.add(new ArrayList(list));
        /* termination condition */
        if (start == nums.length) return;
        
        // {Mistake 1: duplicate elements might be used since nums array is not sorted}
        Set<Integer> set = new HashSet();  // {Correction 1: keep records of used element at the current level}
        /* CHOICES */
        for (int i = start; i < nums.length; i++) {
            /* CONSTRAINTS */
            if (nums[i] < preNum || set.contains(nums[i])) continue;  // avoid duplicates at the current level

            set.add(nums[i]);
            list.add(nums[i]);
            dfs(nums, i + 1, nums[i], list);
            list.remove(list.size() - 1);
        }
    }
}