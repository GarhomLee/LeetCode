https://leetcode.com/problems/summary-ranges/

// 1）维护两个变量：start和end，指向处于同一range的起止
// 2）遍历数组，当发现相邻两数不连续时，将目前的range加入list
// 3）start和end相等和不相等分开处理
// 4）循环结束，再将最后得到的range加入list

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        StringBuilder sb = new StringBuilder();
        String start = String.valueOf(nums[0]), end = String.valueOf(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) end = String.valueOf(nums[i]);
            else {
                sb.append(start);
                if (!start.equals(end)) sb.append("->").append(end);
                list.add(sb.toString());
                sb = new StringBuilder();
                start = String.valueOf(nums[i]);
                end = String.valueOf(nums[i]);
            }
        }
        sb.append(start);
        if (!start.equals(end)) sb.append("->").append(end);
        list.add(sb.toString());
        return list;
    }
}