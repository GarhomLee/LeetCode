https://leetcode.com/problems/rectangle-overlap/

思路：Math
        排除所有rec1在rec2的左、右、上、下的情况即可。
时间复杂度：O(1)
空间复杂度：O(1)

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] || rec1[1] >= rec2[3] || rec1[0] >= rec2[2] || rec1[3] <= rec2[1]);
    }
}