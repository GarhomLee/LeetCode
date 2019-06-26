https://leetcode.com/problems/rotting-oranges/

// 思路：二维数组的BFS，和Tree类似，要利用Queue。
//      先遍历grid数组，找到2的元素的行号和列号，转为一维index后加入Queue。
//      接下来对Queue中元素进行处理。每次拿出Queue的所有元素，记录size，同时维护【临时变量isSpread】表示是否有新的1要变成2。
//      扫描4个相邻元素，如果在界内且值为1，表示在下一层会变成2，因此将这些元素【设为2后再加入Queue，否则会导致重复加入】。同时，
//      因为下一层有了新的元素，所以【isSpread设为true】。当前层处理完后，如果isSpread为true，那么time++。
//      所有2处理完后，检查grid数组是否还有没处理的1。如果有，返回-1，否则返回time。
// 技巧：在第一次遍历grid数组找到2加入Queue的同时，对于1的数量也进行记录。在处理完Queue后，看1的数量是否为0。如果大于0，说明
//      有的1没被处理到，所以返回-1。否则返回time。
// 另一种写法：Queue中存int[]{row, col}，而不需要转换成一维index。

class Solution {
    int[] dir = new int[]{0, -1, 0, 1, 0};
    
    public int orangesRotting(int[][] grid) {
        int rowLen = grid.length, colLen = rowLen == 0? 0 : grid[0].length;
        int time = 0;
        int freshCount = 0;
        Queue<Integer> queue = new LinkedList();
        //Set<Integer> set = new HashSet();  // if 1 is added into Queue instead of 2, Set is necessary to prevent duplicates
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    freshCount++;
                } else if (grid[row][col] == 2) {
                    int index = row * colLen + col;
                    queue.offer(index);
                    //set.add(index);
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isSpread = false;

            while (size-- > 0) {
                int index = queue.poll();
                int row = index / colLen, col = index % colLen;
                for (int i = 0; i < 4; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1], nextIndex = nextRow * colLen + nextCol;
                    if (nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && grid[nextRow][nextCol] == 1) {  // {Correction 1}
                        grid[nextRow][nextCol] = 2;  // the orange got rotten before adding into Queue
                        queue.offer(nextIndex);
                        //set.add(nextIndex);
                        isSpread = true;
                        freshCount--;
                    }
                }
                
            }
            
            if (isSpread) {
                time++;
            }
        }
        
        /*for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    //System.out.println(row + "," +col);
                    return -1;
                }
            }
        }*/
        return freshCount > 0 ? -1 : time;
    }
}