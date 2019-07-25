https://leetcode.com/problems/minesweeper/

// 解法一：BFS with Queue
//         根据题意，给定的click位置只能有两种情况：
//         如果是'M'，即未发现的炸弹，那么变为'X'后就可以直接返回。
//         除此之外，一定是'E'，即未发现的安全区。那么将它变为'B'后，加入Queue，利用Queue进行BFS。
//         对于Queue中poll出的元素，先扫描8个相邻格子，记录炸弹个数count。如果在board范围内发现炸弹'M'，那么count>0，
//         扫描结束后board[row][col]变为相应的count。当前格子不再做后续搜索。
//         如果count==0，没有炸弹，那么向四周扩散搜索。将8个相邻格子中的'E'【先变为'B'，再加入Queue，避免重复搜索】。
//         当Queue为空，表示搜索结束。
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)
// 犯错点：1.实现细节错误：为了防止重复加入Queue中已有的元素，应该在元素加入Queue之前就标记好，当别的元素扫描到这个元素
//             时就可以跳过。

class Solution {
    int[] dir = new int[]{-1, -1, 1, 1, -1, 0, 1, 0, -1};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }
        
        int rowLen = board.length, colLen = rowLen == 0 ? 0 : board[0].length;
        board[row][col] = 'B';
        Queue<Integer> queue = new LinkedList();
        queue.offer(row * colLen + col);  // dimension reduction
        while (!queue.isEmpty()) {
            row = queue.peek() / colLen;
            col = queue.poll() % colLen;
            /* search how many mines around this cell */
            int count = 0;
            for (int i = 0; i < 8; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen || board[nextRow][nextCol] != 'M') continue;  // out of boundary or not a mine
                count++;
            }
            
            if (count > 0) {
                board[row][col] = (char) (count + '0');
            } else {  // no mines around
                //board[row][row] = 'B';  {Mistake 1}
                for (int i = 0; i < 8; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen || board[nextRow][nextCol] != 'E') continue;  // out of boundary or visited
                    
                    board[nextRow][nextCol] = 'B';  // {Correction 1: mark the cell before adding into Queue}
                    queue.offer(nextRow * colLen + nextCol);  // put this adjacent cell for searching at the next level
                }
            }
        }
        return board;
    }
}


解法二：DFS