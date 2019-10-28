https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/

// 思路：Greedy。参考：https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/solution/
//         利用helper method，分别求出将某一面全部变成A[0]或B[0]所需的最小次数，取非-1结果的最小值。
//         在helper method中，维护两个变量：count1表示要将A面变成target所需的次数；count2表示要将B面变成target
//         所需的次数。因此，对于当前位置i，有4种情况：
//         1）A[i]和B[i]都不是target，那么一定不能使得A面或者B面全部为target，因此直接返回-1。
//         2）A[i]不是target，而B[i]是target，那么如果要使得A面全部为target，就需要将其从B[i]翻到A[i]，因此count1++。
//         3）A[i]是target，而B[i]不是target，那么如果要使得B面全部为target，就需要将其从A[i]翻到B[i]，因此count2++。
//         4）A[i]和B[i]都是target，那么不需要翻转，跳过。
//         遍历结束后，返回count1和count2的较小值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int res1 = check(A[0], A, B);
        int res2 = check(B[0], A, B);
        if (res1 != -1 && res2 != -1) {
            return Math.min(res1, res2);
        } else if (res1 == -1) {
            return res2;
        }
        
        return res1;
    }
    
    private int check(int target, int[] A, int[] B) {
        int count1 = 0, count2 = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (A[i] != target && B[i] != target) {
                return -1;
            }
            
            if (A[i] != target) {
                count1++;
            } else if (B[i] != target) {
                count2++;
            }
            
        }
        
        return Math.min(count1, count2);
    }
}