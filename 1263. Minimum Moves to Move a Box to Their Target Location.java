https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/

// 思路：双重BFS。
//         外层BFS：判断箱子是否能到达终点。
//         内层BFS：判断人是否能从一个点到达另外一个点，可能是起始点到最初推箱子的位置，也可能是上一次推箱子的位置到
//             下一次推箱子的位置。
// 时间复杂度：O((rowLen * colLen)^2)
// 空间复杂度：O(rowLen * colLen)
// 犯错点：1.判断条件错误：边界不一定是墙'#'，因此还需要进行越界判断。
//         2.思路错误：即使箱子处于同一个格子，不同的推箱子位置得到的结果可能不同，因此在Queue中需要记录【箱子的位置】和
//             【推箱子人所处的位置】。
//         3.思路错误：和2同理，只有【同样的箱子的位置】和【同样的推箱子人所处的位置】同时出现在HashSet中，才进行去重。
//         4.细节错误：为了判断能否从上一次推箱子的位置到达下一个要推箱子所要站的位置，需要将当前的箱子位置设为【不可通行】，
//             即标记为'#'，否则可能会出现“穿过箱子”去推箱子位置的情况，导致错误。搜索完四个方向后，重置为'.'。
//         5.细节错误：利用Queue中记录的上一次推箱子人所处的位置，判断是否能到达下一个要推箱子所要站的位置，而不是判断从人
//             的起始位置start是否能到推箱子所要站的位置。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    /** check if a person can reach the destination position from the source position */
    private boolean canReach(char[][] grid, int[] source, int[] destination) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(source);
        set.add(source[0] * colLen + source[1]);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (Arrays.equals(curr, destination)) {
                return true;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextRow = curr[0] + dir[i], nextCol = curr[1] + dir[i + 1];  // the next position where the person is going
                int nextIndex = nextRow * colLen + nextCol;
                // if (grid[nextRow][nextCol] == '#' || set.contains(nextIndex)) continue;  // {Mistake 1}
                if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen ||
                    grid[nextRow][nextCol] == '#' || set.contains(nextIndex)) continue;  // {Correction 1}
                
                queue.offer(new int[]{nextRow, nextCol});
                set.add(nextIndex);
            }
        }
        
        return false;
    }
    
    public int minPushBox(char[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        int[] start = new int[2], end = new int[2], player = new int[2];

        // initialize three important positions
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 'B') {
                    start[0] = row;
                    start[1] = col;
                } else if (grid[row][col] == 'T') {
                    end[0] = row;
                    end[1] = col;
                } else if (grid[row][col] == 'S') {
                    player[0] = row;
                    player[1] = col;
                }
            }
        }
        
        Queue<int[][]> queue = new LinkedList<>();  // record both the position of the box and the 
                                                    // position to push the box
        Set<String> set = new HashSet<>();  // same as queue for deduplication

        for (int i = 0; i < 4; i++) {
            int[] push = new int[]{start[0] + dir[i], start[1] + dir[i + 1]};  // 4 possible positions to push the box
            if (!canReach(grid, player, push)) continue;  // skip if the play is not able to reach the push position
            
            //queue.offer(start);  // {Mistake 2}
            queue.offer(new int[][]{start, push});  // {Correction 2}
            // set.add(start[0] * colLen + start[1]);  // {Mistake 3}
            String visited = (start[0] * colLen + start[1]) + "," + (push[0] * colLen + push[1]);
            set.add(visited);  // {Correction 3}
        }
        
        
        int move = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();  // seach each level
            while (size-- > 0) {
                int[][] curr = queue.poll();
                int[] box = curr[0], push = curr[1];
                if (Arrays.equals(box, end)) {
                    return move;
                }
            
                // {Mistake 4}
                grid[box[0]][box[1]] = '#';  // set the box to be impassible  // {Correction 4}
                for (int i = 0; i < 4; i++) {
                    int nextRow = box[0] + dir[i], nextCol = box[1] + dir[i + 1];  // the next position where the box is moved to
                    int[] nextPush = new int[]{box[0] - dir[i], box[1] - dir[i + 1]};  // the next position where the person should stand in order to move the box
                    
                    String visited = (nextRow * colLen + nextCol) + "," + (nextPush[0] * colLen + nextPush[1]);
                    // if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen
                    //     || grid[nextRow][nextCol] == '#' || set.contains(nextRow * colLen + nextCol)
                    //     || !canReach(grid, start, new int[]{curr[0] - dir[i], curr[1] - dir[i + 1]})) continue;  // {Mistake 5}
                    if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen
                        || grid[nextRow][nextCol] == '#' || set.contains(visited)
                        || !canReach(grid, push, nextPush)) continue;  // {Correction 5}

                    queue.offer(new int[][]{new int[]{nextRow, nextCol}, nextPush});
                    set.add(visited);  // {Mistake 3}
                    // set.add(nextRow * colLen + nextCol);  // {Correction 3}
                }
                
                grid[box[0]][box[1]] = '.';  // reset  // {Correction 4}
            }
            move++;  // update move
        }
        
        return -1;  // unable to reach the target position or even the beginning position to push the box
    }
}