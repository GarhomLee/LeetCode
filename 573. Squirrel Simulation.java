https://leetcode.com/problems/squirrel-simulation/

idea: Math
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int getDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
    
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int minDist = Integer.MAX_VALUE;
        int totalDist = 0;
        for (int[] nut : nuts) {
            totalDist += 2 * getDistance(nut, tree);
        }
        
        for (int[] nut : nuts) {
            int dist = totalDist - getDistance(nut, tree) + getDistance(nut, squirrel);
            minDist = Math.min(minDist, dist);
        }
        
        return minDist;
    }
}