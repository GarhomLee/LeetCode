https://leetcode.com/problems/relative-sort-array/

// 思路：TreeMap
//      Map的key为arr1数组元素，value为元素出现次数。用TreeMap是要利用内部的排序特性。
//      先遍历arr1数组，构建Map，然后根据arr2数组的元素出现顺序将元素依次填入res数组，同时将此元素从Map移除。
//      如果Map还有剩余元素，那么就按从小到大顺序继续依次填入res数组。

class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length];
        Map<Integer, Integer> map = new TreeMap();
        for (int n: arr1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        int index = 0;
        for (int n: arr2) {
            int count = map.get(n);
            while (count-- > 0) {
                res[index++] = n;
            }
            map.remove(n);
        }
        for (int n : map.keySet()) {
            int count = map.get(n);
            while (count-- > 0) {
                res[index++] = n;
            }
        }
        return res;
    }
}