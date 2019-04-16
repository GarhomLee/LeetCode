https://leetcode.com/problems/trapping-rain-water-ii/

// 非常精妙的问题，需要用到heap来解决。
// 1）构建class Block，来同时存储高度信息和对应的行列。
// 2）新建PriorityQueue，重写compare
// 3）维护visited变量，表示元素是否已经被访问过
// 4）首先，将最外围的block加入PriorityQueue
// 5）维护maxHeight，表示【当前建起的墙的高度，即当前可以装的水的最大高度】
// 6）将PriorityQueue中的block依次取出，最先取出的高度最低。更新maxHeight，更新visited。
// 7）搜索四个neighbors，找到可能装得到水的block以及装的量。如果neighbor比maxHeight小，意味着地势低，可以装水，
//     装的量为当前maxHeight和neighbor高度的差值。这个差值只与当前的maxHeight有关，因为【maxHeight表示当前建起
//     的墙的高度，代表当前情况下根据短板效应可以装的最大水量】。
// 8）更新res

class Solution {
    int[] dir = new int[]{0, 1, 0, -1, 0};
    public int trapRainWater(int[][] heightMap) {
        int rowLen = heightMap.length, colLen = rowLen == 0? 0 : heightMap[0].length;
        PriorityQueue<Block> pq = new PriorityQueue<>(1, new Comparator<Block>() {
            @Override
            public int compare(Block b1, Block b2) {
                return b1.height - b2.height;
            }
        });
        boolean[][] visited = new boolean[rowLen][colLen];
        /* put all outside blocks into priorityqueue */
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (row == 0 || row == rowLen - 1 || col == 0 || col == colLen - 1) {
                    pq.offer(new Block(row, col, heightMap[row][col]));
                    visited[row][col] = true;
                }
            }
        }
        /* initialization */
        int maxHeight = 0, res = 0;
        /* get the result */
        while (!pq.isEmpty()) {
            Block curr = pq.poll();
            if (curr.height > maxHeight) maxHeight = curr.height;  // update the max height that is available currently, as building up the wall
            for (int i = 0; i < 4; i++) {  // get the neighbors
                int nextRow = curr.row + dir[i], nextCol = curr.col + dir[i + 1];
                if (isValid(nextRow, nextCol, rowLen, colLen) && !visited[nextRow][nextCol]) {  // if the neighbor block is valid and not visited yet, put it into priorityqueue 
                    pq.offer(new Block(nextRow, nextCol, heightMap[nextRow][nextCol]));
                    visited[nextRow][nextCol] = true;
                    if (heightMap[nextRow][nextCol] < maxHeight) res += maxHeight- heightMap[nextRow][nextCol];  // if the neighbor block is lower than the wall, water-trapping is possible
                }
            }
        }
        return res;
    }
    /** determine if a block is valid */
    private boolean isValid(int row, int col, int rowLen, int colLen) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }
    
    class Block {
        int row, col, height;
        Block(int r, int c, int h) {
            row = r;
            col = c;
            height = h;
        }
    }
}