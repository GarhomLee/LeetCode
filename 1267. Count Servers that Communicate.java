https://leetcode.com/problems/count-servers-that-communicate/

思路：Hash Table

时间复杂度：O(rowLen * colLen)
空间复杂度：O(rowLen * colLen)
优化：分别用数组存每行和每列的server个数

class Solution {
    public int countServers(int[][] grid) {
        Map<Integer, List<int[]>> rowMap = new HashMap<>();
        Map<Integer, List<int[]>> colMap = new HashMap<>();
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        
        if (rowLen == 1 || colLen == 1) {
            return 0;
        }
        
        int total = 0;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (grid[row][col] == 1) {
                    rowMap.putIfAbsent(row, new ArrayList<>());
                    rowMap.get(row).add(new int[]{row, col});
                    
                    colMap.putIfAbsent(col, new ArrayList<>());
                    colMap.get(col).add(new int[]{row, col});
                    
                    total++;
                }
            }
        }
        
        int disconnect = 0;
        for (int row : rowMap.keySet()) {
            if (rowMap.get(row).size() > 1) continue;
            
            int[] pair = rowMap.get(row).get(0);
            int col = pair[1];
            
            if (colMap.get(col).size() > 1) continue;
            disconnect++;
        }
        
        return total - disconnect;
    }
}