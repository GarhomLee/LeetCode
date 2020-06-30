https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/

idea: TreeMap + HashMap
time complexity: O(n log n + k)
space complexity: O(k)

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int sum = 0;
        for (int key : treeMap.keySet()) {
            sum += treeMap.get(key);
            treeMap.put(key, sum);
        }
        
        for (int i = 0; i < n; i++) {
            res[i] = treeMap.get(nums[i]) - map.get(nums[i]);
        }
        
        return res;
    }
}