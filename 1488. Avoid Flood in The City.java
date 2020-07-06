https://leetcode.com/problems/avoid-flood-in-the-city/

idea: HashMap + TreeSet
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Map<Integer, Integer> map = new HashMap<>();    // num->its previous occurrence
        TreeSet<Integer> drySet = new TreeSet<>();  //  idx of rains[idx] == 0
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                drySet.add(i);
            } else {
                if (map.containsKey(rains[i])) {
                    Integer ceiling = drySet.ceiling(map.get(rains[i]));
                    if (ceiling == null) {
                        return new int[0];
                    }
                    res[ceiling] = rains[i];
                    drySet.remove(ceiling);
                }
                
                res[i] = -1;
                map.put(rains[i], i);
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (res[i] == 0) {
                res[i] = 1;
            }
        }
        return res;
    }
}