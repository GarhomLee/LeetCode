https://leetcode.com/problems/minimum-time-difference/

// 思路：Sort
//      先同一转换成分钟，然后直接排序，遍历求最小间隔。最后一个数和第一个数的间隔要特殊处理。

class Solution {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList();
        for (String str: timePoints) {
            String[] split = str.split(":");
            int time = 60 * Integer.valueOf(split[0]) + Integer.valueOf(split[1]);
            list.add(time);
        }
        Collections.sort(list);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            minDiff = Math.min(minDiff, list.get(i) - list.get(i - 1));
        }
        minDiff = Math.min(minDiff, list.get(0) + 24 * 60 - list.get(list.size() - 1));
        return minDiff;
    }
}