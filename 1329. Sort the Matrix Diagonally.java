https://leetcode.com/problems/sort-the-matrix-diagonally/

思路：HashMap + PriorityQueue
        根据规律，【处于同一对角线的元素都可以放在同一个组中，组的id为row-col】。
        因此，只要对同组的元素排序，再按从前到后依次放回matrix即可。
时间复杂度：O(rowlen * collen * log(D)), D=length of diagonal=min(rowlen * collen)
空间复杂度：O(rowlen * collen)

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int rowlen = mat.length, collen = rowlen == 0 ? 0 : mat[0].length;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        
        for (int row = 0; row < rowlen; row++) {
            for (int col = 0; col < collen; col++) {
                int id = row - col;
                map.putIfAbsent(id, new PriorityQueue<>());
                map.get(id).offer(mat[row][col]);
                
            }
        }
        
        for (int row = 0; row < rowlen; row++) {
            for (int col = 0; col < collen; col++) {
                int id = row - col;
                mat[row][col] = map.get(id).poll();
            }
        }
        
        return mat;
    }
}