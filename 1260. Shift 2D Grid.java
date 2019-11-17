https://leetcode.com/problems/shift-2d-grid/

// 思路：Math
// 时间复杂度：O(rowLen * colLen)
// 空间复杂度：O(rowLen * colLen)
// 犯错点：1.细节错误：题目要求右移k位，而不是左移k位，因此假设k < rowLen * colLen，新的起始元素位置应该
//             为rowLen * colLen - k。

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rowLen = grid.length, colLen = rowLen == 0 ? 0 : grid[0].length;
        List<Integer> list = new ArrayList<>();
        for (int[] line : grid) {
            for (int num : line) {
                list.add(num);
            }
        }
        
        List<List<Integer>> res = new ArrayList<>();
        //int index = k % (rowLen * colLen);  // {Mistake 1}
        int index = ((rowLen * colLen) - k % (rowLen * colLen)) % (rowLen * colLen);  // {Correction 1}
        for (int row = 0; row < rowLen; row++) {
            List<Integer> line = new ArrayList<>();
            for (int col = 0; col < colLen; col++) {
                line.add(list.get(index));
                index = (index + 1) % (rowLen * colLen);
            }
            res.add(line);
        }
        
        
        return res;
    }
}