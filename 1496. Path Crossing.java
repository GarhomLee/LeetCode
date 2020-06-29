https://leetcode.com/problems/path-crossing/

idea: HashSet
time complexity: O(n)
space complexity: O(n)

class Solution {
    public boolean isPathCrossing(String path) {
        int[] curr = new int[]{0, 0};
        Set<String> set = new HashSet<>();
        set.add(curr[0] + "," + curr[1]);
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'N':
                    curr[1]++;
                    break;
                case 'S':
                    curr[1]--;
                    break;
                case 'E':
                    curr[0]++;
                    break;
                case 'W':
                    curr[0]--;
                    break;
            }
            
            String currPos = curr[0] + "," + curr[1];
            if (set.contains(currPos)) {
                return true;
            }
            set.add(currPos);
        }
        
        return false;
    }
}