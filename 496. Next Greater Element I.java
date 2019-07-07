https://leetcode.com/problems/next-greater-element-i/

// 解法一：HashMap，常规遍历
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        
        Map<Integer, Integer> map = new HashMap();
        for (int n: nums1) {
            map.put(n, -1);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) map.put(nums2[i], i);
        }
        
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = map.get(nums1[i]) + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
            }
        }
        return res;
    }
}

解法二：Stack + HashMap
时间复杂度：O(n)
空间复杂度：O(n)