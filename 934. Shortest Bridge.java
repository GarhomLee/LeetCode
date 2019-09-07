https://leetcode.com/problems/shortest-bridge/

// 思路：DFS + BFS
//         step1:对其中一座岛进行DFS，用另一个数字作为mark将1替换。这是为了区分两座岛，使得BFS时不会因为遇到同一座岛
//             而错误地提前停止搜索。
//             在DFS的同时，将这个岛的元素的二维坐标转为一维index放入Queue中，作为BFS的起始seed。
//             注意：只需要对一座岛进行DFS，如果发现了某一座岛，就可以break了。
//         step2:利用Queue进行BFS，将每一步扫描到的0变成3，表示该位置已经被搜索过，直到找到元素1的岛。
// 时间复杂度：O(m*n), m=matrix.length, n=matrix[0].length
// 空间复杂度：O(m*n), m=matrix.length, n=matrix[0].length
// 犯错点：1.细节错误：两重for循环，只在内层break的话外层还会进行下一次循环。需要再加一个break。在外层break之前，
//             需要加一个判断条件，只有在内层break了之后外层才能break。

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    Queue<Integer> queue = new LinkedList<>();
    
    public int shortestBridge(int[][] matrix) {
    int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
    int count = 0;

    /* transform one of two islands to another mark 2 */
    boolean isTransformed = false;
    for (int row = 0; row < rowLen; row++) {
        for (int col = 0; col < colLen; col++) {
            if (matrix[row][col] == 1) {
                dfs(matrix, row, col, 2);
                isTransformed = true;
                break;
            }
        }
        
        // {Mistake 1}
        if (isTransformed) {
            break;
        }  // {Correction 1}
    }
    
    /* BFS */
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int index = queue.poll();
        int row = index / colLen, col = index % colLen;
        
        for (int i = 0; i < 4; i++) {
          int nextRow = row + dir[i], nextCol = col + dir[i + 1];
          if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen 
              || matrix[nextRow][nextCol] == 2 || matrix[nextRow][nextCol] == 3) {
            continue;
          }
          
          if (matrix[nextRow][nextCol] == 1) {
            return count;
          }
          
          matrix[nextRow][nextCol] = 3;
          queue.offer(nextRow * colLen + nextCol);
        }
      }
      
      count++;
    }
    
    return -1;
    //throw new exception();
  }
  
  /** helper method for transforming one of two islands to another mark 2 as well as adding seeds for BFS */
  private void dfs(int[][] matrix, int row, int col, int target) {
    if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] != 1) {
      return;
    }
    
    int rowLen = matrix.length, colLen = rowLen == 0 ? 0 : matrix[0].length;
    matrix[row][col] = target;  // change current element 1 to target
    queue.add(row * colLen + col);
      
    for (int i = 0; i < 4; i++) {
      int nextRow = row + dir[i], nextCol = col + dir[i + 1];
      dfs(matrix, nextRow, nextCol, target);
    }
  }
}