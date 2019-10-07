https://leetcode.com/problems/robot-room-cleaner/

// 思路：DFS (Recursion)
//         递归函数定义：void dfs(Robot robot, int row, int col, Set<String> visited, int dir)，
//             表示Robot从当前位置{row,col}出发，面向的方向为dir，扫描所有联通的格子。
//         终止条件：当前位置已经被扫描过，即存在于visited中，什么都不做直接return。
//         递归过程：有4个可选方向，因此需要先确定目前面向的方向dir，【在确定方向dir的下一步可以走后】，走到
//             下一步的格子{nextRow, nextCol}，调用递归函数扫描从下一步的格子开始的所有连通格子，【然后
//             回到当前格子】。
//             不管当前方向dir是否可以走，在下一次尝试搜索前都需要更新dir为下一个方向，同时让robot右转向。
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)
// 犯错点：1.初始化错误：nextRow和nextCol应该在每次尝试新的方向前都要初始化为row和col，而不能只初始化一次。

class Solution {
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0, new HashSet<>(), 0);
    }
    
    private void dfs(Robot robot, int row, int col, Set<String> visited, int dir) {
        String curr = "" + row + "#" + col;
        if (visited.contains(curr)) {
            return;
        }
        
        visited.add(curr);  // mark as visited
        robot.clean();
        
        //int nextRow = row, nextCol = col;  // {Mistake 1}
        for (int i = 0; i < 4; i++) {
            int nextRow = row, nextCol = col;  // {Correction 1}
            if (robot.move()) {
                if (dir == 0) {
                    nextRow -= 1;
                } else if (dir == 1) {
                    nextCol += 1;
                } else if (dir == 2) {
                    nextRow += 1;
                } else {
                    nextCol -= 1;
                }
                
                dfs(robot, nextRow, nextCol, visited, dir);
                stepback(robot);
            }
            
            dir = (dir + 1) % 4;  // next direction
            robot.turnRight();
        }
    }
    
    private void stepback(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    } 
}