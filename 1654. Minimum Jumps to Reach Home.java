https://leetcode.com/problems/minimum-jumps-to-reach-home/

idea: BFS
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = new HashSet<>();
        for (int pos : forbidden) {
            forbiddenSet.add(pos);
        }
        Queue<int[]> queue = new LinkedList<>();    // [pos, isBackward]
        Set<String> visited = new HashSet<>();  // must also record the direction state
        queue.offer(new int[]{0, 0});
        visited.add("0,0");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                int currPos = curr[0], isBackward = curr[1];
                if (currPos == x) {
                    return step;
                }
                
                if (currPos <= 10000
                    &&!forbiddenSet.contains(currPos + a)
                   && !visited.contains(currPos + a + ",0")) {
                    queue.offer(new int[]{currPos + a, 0});
                    visited.add(currPos + a + ",0");
                }
                if (isBackward != 1
                     && currPos - b >= 0
                   && !forbiddenSet.contains(currPos - b)
                   && !visited.contains(currPos - b+ ",1")) {
                    queue.offer(new int[]{currPos - b, 1});
                    visited.add(currPos - b+ ",1");
                }
            }
            step++;
        }
        
        return -1;
    }
}