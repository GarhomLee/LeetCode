https://leetcode.com/problems/check-array-formation-through-concatenation/

idea: HashMap
time complexity: O(n)
space complexity: O(n)

class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();  // first int -> piece arr
        for (int[] p : pieces) {
            map.put(p[0], p);
        }
        
        int i = 0;
        while (i < arr.length) {
            if (!map.containsKey(arr[i])) {
                return false;
            }
            
            int[] p = map.get(arr[i]);
            for (int j = 0; j < p.length; j++, i++) {
                if (arr[i] != p[j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
}