https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/

idea: TreeSet
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int[] p : points) {
            treeSet.add(p[0]);
        }
        
        int prev = Integer.MAX_VALUE;
        int maxWidth = Integer.MIN_VALUE;
        for (int curr : treeSet) {
            maxWidth = Math.max(maxWidth, curr - prev);
            prev = curr;
        }
        
        return maxWidth;
    }
}