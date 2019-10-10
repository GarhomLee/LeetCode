https://leetcode.com/problems/design-snake-game/

// 思路：Queue + HashSet
//     维护变量：
//     1）rowLen, colLen，确定游戏的范围
//     2）row, col，表示当前蛇头的位置，初始值为{0, 0}
//     3）food，表示所有食物的位置；fIndex，表示当前显示的食物，初始值为0；score，表示当前所得的分数，初始值为0。
//     4）queue，储存当前蛇所占的位置的一维index，用来进行蛇的“移动”。queue尾部为蛇头，queue头部为蛇尾。初始情况加入0。
//     5）set，储存当前蛇所占的位置的一维index，用来检查蛇是否撞上了自己。初始情况加入0。
//     对于int move(String direction)的实现：
//     step1: 根据direction，更新蛇头位置{row, col}。如果越界，直接返回-1表示游戏结束。
//     step2: 根据新的蛇头位置{row, col}是否搜索到食物，决定是否需要将蛇尾移除。有两种情况：
//         1）没找到食物，那么从queue和set中移除蛇尾。这时候，如果利用set判断出蛇仍然撞上了自己，那么返回-1.
//         2）找到了食物，不许需要从queue和set中移除蛇尾，而需要更新score++和fIndex++。
//         无论哪种情况，只要没有game over，都需要将蛇头加进queue和set。
//         最后返回score。
// 时间复杂度：O(k), k=num of move call
// 空间复杂度：O(n), n=num of food
// 犯错点：1.题目理解错误：检查蛇是否撞上了自己时，是基于“尾部先向前挪，头部再向前挪”的运动顺序，所以需要先移除蛇尾。

class SnakeGame {
    int rowLen, colLen;
    int row, col;
    int[][] food;
    int fIndex;
    int score;
    Queue<Integer> queue;
    Set<Integer> set;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        rowLen = height;
        colLen = width;
        this.food = food;
        fIndex = 0;
        score = 0;
        row = 0;
        col = 0;
        queue = new LinkedList<>();
        set = new HashSet<>();
        queue.offer(0);
        set.add(0);
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        // int nextRow = row, nextCol = col;
        char dir = direction.charAt(0);
        switch (dir){
            case 'U':
                row--;
                break;
            case 'L':
                col--;
                break;
            case 'R':
                col++;
                break;
            case 'D':
                row++;
                break;
        }
        
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen) {  // hit the wall
            return -1;
        }
        
        int pos = row * colLen + col;

        if (fIndex >= food.length || (food[fIndex][0] * colLen + food[fIndex][1]) != pos) {  // not hit food
            // first remove the tail
            int tailPos = queue.poll();
            set.remove(tailPos);
            if (set.contains(pos)) {  // still hit itself after removing tail
                return -1;
            }
        } else {  // hit the food
            score++;
            fIndex++;
        }
        
        // head move forward
        queue.offer(pos);
        set.add(pos);
        
        return score;  // {Correction 1}

        /*int pos = row * colLen + col;
        if (row < 0 || row >= rowLen || col < 0 || col >= colLen
            || set.contains(pos)) {  // game over
            return -1;
        }
        
        queue.offer(pos);
        set.add(pos);
        if (fIndex >= food.length || (food[fIndex][0] * colLen + food[fIndex][1]) != pos) {  // not hit food
            int tailPos = queue.poll();
            set.remove(tailPos);
            return score;
        }
        
        // hit the food
        score++;
        fIndex++;
        return score;*/  // {Mistake 1}
    }
    
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */