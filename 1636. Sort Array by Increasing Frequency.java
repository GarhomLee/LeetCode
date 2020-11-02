https://leetcode.com/problems/sort-array-by-increasing-frequency/

idea: Sort (use stream function)
time complexity: O(n log n)
space complexity: O(n) for sorting

class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        nums = Arrays.stream(nums).
            boxed().
            sorted((a, b) -> map.get(a) == map.get(b) ? b - a : map.get(a) - map.get(b)). // customized sort 
            mapToInt(i -> i).
            toArray();
        return nums;
    }
}