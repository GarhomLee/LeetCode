https://leetcode.com/problems/sliding-puzzle/

// 思路：BFS。视频讲解：https://www.youtube.com/watch?v=ABSjW0p3wsM
//         关键点：将board状态转成String。
//         step1: 先遍历一次board，将二维数字矩阵转化为一维字符串，同时构建目标字符串。
//                 判断特殊情况：初始状态和目标状态如果相等，直接返回0.
//         step2: 维护HashSet，记录遍历过的状态。
//                 将初始状态加入Queue和HashSet，同时步数step初始化为1.
//         step3: 对于每一层的状态，遍历'0'对应的二维坐标的和4个相邻格子交换后的状态。
//                 如果找到了目标状态，直接返回step。
//                 否则，将没有搜索过的状态先记录在HashSet中，然后加入Queue。
// 时间复杂度：O((m*n)!)
// 空间复杂度：O((m*n)!)
// 优化点：A*，计算下一个可能状态距离目标状态的mahattan distance，然后用PriorityQueue代替普通的Queue，每次取出
//         距离最近的一个状态。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    int ROW_LEN = 2;
    int COL_LEN = 3;
    
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (int row = 0; row < ROW_LEN; row++) {
            for (int col = 0; col < COL_LEN; col++) {
                sb1.append(board[row][col]);
                sb2.append(row * COL_LEN + col + 1);
            }
        }
        sb2.setCharAt(ROW_LEN * COL_LEN - 1, '0');  // replace the last '6' with '0'
        String curr = sb1.toString(), goal = sb2.toString();
        if (curr.equals(goal)) {
            return 0;
        }
        
        Set<String> visited = new HashSet<>();
        visited.add(curr);
        Queue<String> queue = new LinkedList<>();
        queue.offer(curr);
        
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                curr = queue.poll();
                int index = curr.indexOf('0');
                int row = index / COL_LEN, col = index % COL_LEN;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    if (nextRow < 0 || nextRow >= ROW_LEN || nextCol < 0 || nextCol >= COL_LEN) continue;
                    
                    int nextIndex = nextRow * COL_LEN + nextCol;
                    String next = swap(curr, index, nextIndex);
                    
                    if (next.equals(goal)) {
                        return step;
                    }
                    if (visited.contains(next)) continue;
                    
                    visited.add(next);
                    queue.offer(next);
                }
            }
            
            step++;
        }
        
        return -1;
    }
    
    private String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
    }
}