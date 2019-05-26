https://leetcode.com/problems/intersection-of-two-arrays/

// 解法一：Set
// 1）把nums1中的元素加入set达到去重的目的
// 2）遍历nums2，当set中有nums2的元素，说明有intersection，加入list，并从set中移除，即使再次遇到相同元素也不会再加入list
// 3）把list转化为array
// 时间复杂度：O(m+n), m=nums1.length, n=nums2.length
// 空间复杂度：O(m), m=nums1.length

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

// 解法二：Binary Search

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) return new int[0];
        
        List<Integer> list = new ArrayList();
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet();
        for (int n: nums1) {
            set.add(n);
        }
        
        for (int n: set) {
            int index = Arrays.binarySearch(nums2, n);
            if (index >= 0) list.add(n);
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}