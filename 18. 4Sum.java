https://leetcode.com/problems/4sum/

// 可以作为3Sum的另一个follow-up。实际上，在kSum问题中，k >= 3时，可以利用recursion，写出一般形式的generalized代码，将k代入相应的具体数值即可。
// 1）由于不考虑index的影响，所以可以先sort
// 2）用helper method，用recursion将k = 4代入
// 3）helper method中，最关键的是维护一个临时的List<Integer> list = new ArrayList<>();
// 4）当k > 2时，临时list加入当前的数，用recursion计算k - 1，然后将当前数从list移除（类似backtracking）。遍历至nums.length - k + 1个数，注意避免搜索重复的数
// 5）当k == 2时，reduce成类2sum问题。当target == nums[left] + nums[right]时，加入临时list，再把这个list加入resultList里：resultList.add(new ArrayList(list)); 然后将nums[left]和nums[right]从list移除。注意避免搜索重复的数
// 6）Time complexity: O(n^2)

class Solution {
    List<List<Integer>> resultList = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) return resultList;
        Arrays.sort(nums);
        sum(4, nums, target, 0, new ArrayList<Integer>());
        return resultList;
    }
     
    private void sum(int k, int[] nums, int target, int start, List<Integer> list) {
        if (k == 2){
            int left = start, right = nums.length - 1;
            while (left < right) {
                if (target > nums[left] + nums[right]) left++;
                else if (target < nums[left] + nums[right]) right--;
                else {
                    list.add(nums[left]);
                    list.add(nums[right]);
                    resultList.add(new ArrayList<Integer>(list));
                    list.remove(list.size() - 1);
                    list.remove(list.size() - 1);
                    do left++; while (left < right && nums[left] == nums[left - 1]);
                    do right--; while (left < right && nums[right] == nums[right + 1]);
                }
            }
        } else if (k > 2) {
            for (int i = start; i < nums.length - k + 1; i++) {
                if (i == start || nums[i] != nums[i - 1]) {
                    list.add(nums[i]);
                    sum(k - 1, nums, target - nums[i], i + 1, list);
                    list.remove(list.size() - 1);
                }
            }
        } else return;
    }
}