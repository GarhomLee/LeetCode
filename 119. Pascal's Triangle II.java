https://leetcode.com/problems/pascals-triangle-ii/

idea: DP?
time comp: O(rowIndex^2)
space comp: O(rowIndex)

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<>();
        for (int row = 0; row <= rowIndex; row++) {
            ret.add(ret.size(), 1);
            for (int col = row - 1; col >= 1; col--) {
                ret.set(col, ret.get(col) + ret.get(col - 1));
            }
        }
        
        return ret;
    }
}