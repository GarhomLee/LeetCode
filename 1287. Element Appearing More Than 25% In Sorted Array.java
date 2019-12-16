https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/

// 解法一：HashMap
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.思路错误：不能用vote algo

class Solution {
    public int findSpecialInteger(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > n / 4) {
                return num;
            }
        }
        
        return -1;
    }
}


解法二：Binary Search (in index)