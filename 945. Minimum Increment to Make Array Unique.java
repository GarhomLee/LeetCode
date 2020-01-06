https://leetcode.com/problems/minimum-increment-to-make-array-unique/

思路：Math (Greedy?) + Sort
        观察到，对于[1,1,2,2,3]，不管是变成[1,2,3,4,5]，还是[1,4,5,2,3]，增量都是一样的，因此可以采用
        “贪心”的做法。
        首先对数组排序，维护变量min表示当前需要增加到的最小值。如果当前值num < min，那么需要的增量为min-num。
        如果num >= min，后面的数都会比min大，因此不需要增量，而要把min更新为num。
时间复杂度：O(n log n)
空间复杂度：O(1)
优化：见：https://leetcode.com/problems/minimum-increment-to-make-array-unique/discuss/197687/JavaC%2B%2BPython-Straight-Forward

class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int min = 0, res = 0;
        for (int num : A) {
            if (num > min) {
                min = num;
            } else {
                res += (min - num);
            }
            min++;
        }
        return res;
    }
}