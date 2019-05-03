https://leetcode.com/problems/combination-sum-iii/

// 40的follow-up。

// 1）choices：1到9（无重复）；goal：取k个数，使和为n；constraints：不能走回头路
// 2）当k < 0或n < 0时返回；当k == 0 && n == 0时加入resultList
// 3）下一层遍历从当前元素的下一个元素开始

class Solution {
    List<List<Integer>> resultList = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k > 9) return resultList;
        find(k, n, new ArrayList<Integer>(), 1);
        return resultList;
    }
    private void find(int k, int n, List<Integer> list, int start) {
        if (n < 0 || k < 0) return;
        if (n == 0 && k == 0) {
            resultList.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = start; i < 10; i++) {
                list.add(i);
                find(k - 1, n - i, list, i + 1);
                list.remove(list.size() - 1);
        }
    }
}