https://leetcode.com/problems/combination-sum/

// 属于backtracking问题。

// 1）choices：candidates数组里的数（无重复）；goal：target；constraints：不能走回头路
// 2）因为candidates中没有重复的数，所以不用sort
// 3）每层遍历从当前的元素开始（而不是从candidates[0]开始）

class Solution {
    List<List<Integer>> resultList = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //if (candidates == null || candidates.length == 0) return resultList;
        find(candidates, target, new ArrayList<Integer>(), 0);
        return resultList;
    }
    private void find(int[] candidates, int target, List<Integer> list, int start) {
        if (target < 0) return;
        if (target == 0) {
            resultList.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            find(candidates, target - candidates[i], list, i);
            list.remove(list.size() - 1);
        }
    }
}