https://leetcode.com/problems/maximum-distance-in-arrays/

idea: Info Cache
    -Keep track of the min first, as well as the max last, in arrays[0:i-1], and update maxDist
     by max(maxDist, first of arrays[i], last of arrays[i]), and then update minFirst and maxLast.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> list = arrays.get(0);
        int minFirst = list.get(0), maxLast = list.get(list.size() - 1);
        int maxDist = 0;
        for (int i = 1; i < arrays.size(); i++) {
            list = arrays.get(i);
            int first = list.get(0), last = list.get(list.size() - 1);
            maxDist = Math.max(maxDist, Math.max(maxLast - first, last - minFirst));
            maxLast = Math.max(maxLast, last);
            minFirst = Math.min(minFirst, first);
        }
        
        return maxDist;
    }
}