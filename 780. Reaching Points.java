https://leetcode.com/problems/reaching-points/

思路：Math。参考：https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat

时间复杂度：O(log n), n=max(tx, ty)
空间复杂度：O(1)

class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        
        return (sx == tx && ty >= sy && (ty - sy) % sx == 0) 
            || (sy == ty && tx >= sx && (tx - sx) % sy == 0);
    }
}