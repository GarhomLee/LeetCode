https://leetcode.com/problems/maximum-width-ramp/solution/

idea: 2-pass TreeMap
time complexity: O(N log N)
space complexity: O(N)

class Solution {
    public int maxWidthRamp(int[] A) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = A.length, max = -1, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] > max) {
                max = A[i];
                treeMap.put(max, i);
            }
        }
        
        for (int i = 0; i < n; i++) {
            Integer ceilingKey = treeMap.ceilingKey(A[i]);
            int ceilingIdx = treeMap.get(ceilingKey);
            if (ceilingIdx > i) {
                ret = Math.max(ret, ceilingIdx - i);
            }
        }
        
        return ret;
    }
}


improvement: 1-pass TreeMap

class Solution {
    public int maxWidthRamp(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); 
        int dist = 0;
        for(int i = 0; i < A.length; ++i){
            if(map.floorKey(A[i]) == null)
                map.put(A[i], i);
            else 
                dist = Math.max(dist, i - map.get(map.floorKey(A[i])));
        }
        return dist;
    }
}