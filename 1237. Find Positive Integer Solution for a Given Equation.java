https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/

// 思路：转化为给定二维数组matrix，数组里的值分别按row和col递增，找到所有{x,y}使得matrix[x][y]==z。参考：https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/discuss/414249/JavaC%2B%2BPython-O(X%2BY)
//         从matrix右上角开始，已知同行的左边元素一定比它小，同列下方元素一定比它大。可能有三种情况：
//         1）f(x,y) > z，说明需要减小f(x,y)。因为y从最大值开始，因此可以通过减小y来减小f(x,y)。
//         2）f(x,y) < z，说明需要增加f(x,y)。因为x从最小值开始，因此可以通过增加x来增加f(x,y)。
//         3）f(x,y) == z，找到了一个解，加入res。由于已知f(x, y) < f(x + 1, y)，且f(x, y) < f(x, y + 1)，且
//             因此f(x+1, y)一定大于f(x, y)，而f(x, y-1)一定小于f(x, y)，都不可能是z，所以可以直接x++，同时y--。
// 时间复杂度：O(x + y)
// 空间复杂度：O(max(x, y))

/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
 *     public int f(int x, int y);
 * };
 */
class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int x = 1, y = 1000;
        while (x <= 1000 && y >= 1) {
            int ans = customfunction.f(x, y);
            if (ans > z) {
                y--;
            } else if (ans < z) {
                x++;
            } else {
                res.add(Arrays.asList(x++, y--));
            }
        }
        
        return res;
    }
}