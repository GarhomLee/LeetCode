https://leetcode.com/problems/intersection-of-two-arrays/

// 1）把nums1中的元素加入set达到去重的目的
// 2）遍历nums2，当set中有nums2的元素，说明有intersection，加入list，并从set中移除，即使再次遇到相同元素也不会再加入list
// 3）把list转化为array

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int n: nums1) {
            set.add(n);
        }
        for (int n: nums2) {
            if (set.contains(n)) {
                list.add(n);
                set.remove(n);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}