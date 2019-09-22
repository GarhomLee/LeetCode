https://leetcode.com/problems/minimum-knight-moves/

// 思路：BFS
//         step0:维护Queue，对于二维坐标进行BFS。维护Set，记录搜索过的二维坐标的一维表示，防止重复搜索相同的坐标。
//             维护变量move，表示当前走了多少步，初始化为0。维护全局变量dx和dy，{dy[i],dx[i]}表示其中一个下一步
//             可以走的位置。
//         step1:不断取出Queue中的同一层的元素，如果搜索到了目标{x,y}，那么可以返回move。
//         step2:如果没搜索到目标，那么遍历8个下一步可以走的位置，跳过超出题目范围|x|+|y|<=300或者已经遍历过的
//             记录在Set中的位置，将下一步坐标{nextRow, nextCol}加入Queue中作为下一层元素，同时将其一维的坐标
//             表示加入Set中用来去重。
//         step3:搜索完这一层，没有找到目标，那么更新move++。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n^2)
// 犯错点：1.细节错误：用dimension reduction将二维坐标降为一维坐标，再转换回二维时，由于二维坐标中存在负数，因此
//             可能出错。如：COL_LEN = 1000，{1,-2} -> 1*1000-2=998 -> {998/COL_LEN,998%COL_LEN}={0,998}。
//             因此，只能利用二维坐标的一维降维作为去重的手段，而不能加入Queue中进行BFS。

class Solution {
    int[] dx = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
    int[] dy = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
    int COL_LEN = 1000;
    
    public int minKnightMoves(int x, int y) {
        /*Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);*/  // {Mistak 1}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});  // {Correction 1}
        Set<Integer> set = new HashSet<>();
        set.add(0);
        int move = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                
                if (curr[0] == x && curr[1] == y) {
                    return move;
                }

                for (int i = 0; i < 8; i++) {
                    int nextRow = curr[1] + dy[i], nextCol = curr[0] + dx[i];
                    int nextIndex = nextRow * COL_LEN + nextCol;
                    
                    if (Math.abs(nextRow) + Math.abs(nextCol) > 300 || set.contains(nextIndex)) continue;
                    
                    queue.offer(new int[]{nextRow, nextCol});
                    set.add(nextIndex);
                }
            }
            
            move++;
        }
        
        return -1;
    }
}