https://leetcode.com/problems/combination-sum-ii/

// 39的follow-up。
// 1）choices：candidates数组里的数（有重复）；goal：target；constraints：不能走回头路
// 2）因为candidates有重复的数，所以【需要sort】
// 3）每层遍历从当前的元素开始（而不是从candidates[0]开始），遍历时需要找到下一个非重复的数

class Solution {
    List<List<Integer>> resultList = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
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
            if (i == start || candidates[i] != candidates[i - 1]) {
                list.add(candidates[i]);
                find(candidates, target - candidates[i], list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}