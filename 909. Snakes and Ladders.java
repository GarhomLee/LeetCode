https://leetcode.com/problems/snakes-and-ladders/

思路：BFS。涉及到最短步数、路径之类的，首选BFS。

时间复杂度：O(n^2), n=board.length=board[0].length
空间复杂度：O(n^2), n=board.length=board[0].length
犯错点：1.思路错误：如果用DFS，不好解决遍历到当前正在遍历的路径上的问题。

class Solution {
    final int DICE = 6;
    
    public int snakesAndLadders(int[][] board) {
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        
        /* convert a boustrophedonically arranged board to a 1-d array */
        int[] arr = new int[rowLen * colLen + 1];
        int index = 1;
        for (int row = rowLen - 1; row >= 0; row--) {
            if (row % 2 == (rowLen - 1) % 2) {
                for (int col = 0; col < colLen; col++) {
                    arr[index++] = board[row][col];
                }
            } else {
                for (int col = colLen - 1; col >= 0; col--) {
                    arr[index++] = board[row][col];
                }
            }
        }
        
        /* perform bfs and return the result */
        return bfs(arr, rowLen * colLen);
    }
    
    private int bfs(int[] arr, int target) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();  // keep track of visited elements
        
        queue.offer(1);  // started from 1
        set.add(1);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();  // get all elements in the current step
            while (size-- > 0) {
                int curr = queue.poll();
                if (curr == target) {  // target found
                    return step;
                }
                
                for (int i = 1; i <= DICE; i++) {
                    if (curr + i > target) continue;  // skip those out of boundary
                    
                    int next = arr[curr + i] == -1 ? curr + i : arr[curr + i];
                    if (set.contains(next)) continue;  // skip visited elements
                    
                    queue.offer(next);
                    set.add(next);
                }
            }
            
            step++;
        }
        
        return -1;  // cannot reach the target
    }
}