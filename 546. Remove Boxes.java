https://leetcode.com/problems/remove-boxes/

// 对比：664. Strange Printer

// 思路：Recursion with Memoization。难度较高。视频讲解：https://www.youtube.com/watch?v=wT7aS5fHZhs
//         递归函数定义：int dfs(int[] boxes, int start, int end, int k, int[][][] dp)表示求出boxes[start:end]
//                     且boxes[end]右边有【连续k个】和boxes[end]颜色相同【且紧接boxes[end]】的箱子这样条件下的最大的分数。
//                     辅助数组dp[start][end][k]定义相同。
//                     根据题意，题目要求解的就是dfs(boxes, 0, len - 1, 0, dp)。
//         终止条件：1）start > end，越界，返回0
//                 2）dp[start][end][k] > 0，表示已经求解过了，直接返回
//         递归过程：找分割点，这个分割点boxes[p]和boxes[end]颜色相同，使得范围分为boxes[p+1:end-1]和boxes[start:p]+boxes[end]
//                 两部分。
//                 1）对于中间部分的boxes[p+1:end-1]，可以发现不存在连续的和boxes[end-1]颜色相同且紧接它的箱子，因此
//                     这部分调用的递归函数是dfs(boxes, p + 1, end - 1, 0, dp)，模拟的是先抽走中间部分的过程。
//                 2）对于另外一部分，调用的递归函数是dfs(boxes, start, p, k + 1, dp)，将boxes[end]连同后面紧接的k个
//                     同色箱子合成k+1个箱子，接在boxes[p]后面，模拟的是中间部分被抽走后前后两端拼接得到新的连续同色箱子的过程。
//                 综合两部分，maxScore是这两部分得到的分数的和。对于boxes[start:end)，以所有和boxes[end]同色的boxes[p]作为
//                 分割点，maxScore取不同分割点得到的和的最大值。
//                 maxScore初始化为特殊情况，boxes[start:end)没有一个和boxes[end]同色的boxes[p]，那么最大分数为直接将boxes[end]和后面紧接的
//                 k个箱子一起拿走，即dfs(boxes, start, end - 1, 0, dp) + (k + 1) * (k + 1)。
// 优化：将end位置移到从右向左的第一个boxes[end] != boxes[end - 1]的位置，保证了boxes[end]和boxes[end - 1]颜色不同，
//         减少分割点的选择。
// 时间复杂度：O(n^4)
// 空间复杂度：O(n^3)

class Solution {
    public int removeBoxes(int[] boxes) {
        int len = boxes.length;
        int[][][] dp = new int[len][len][len];
        
        return dfs(boxes, 0, len - 1, 0, dp);
    }
    
    private int dfs(int[] boxes, int start, int end, int k, int[][][] dp) {
        if (start > end) {
            return 0;
        }
        if (dp[start][end][k] > 0) {
            return dp[start][end][k];
        }
        
        while (start < end && boxes[end] == boxes[end - 1]) {  // optimization
            end--;
            k++;
        }
        /* case 1 */
        int maxScore = dfs(boxes, start, end - 1, 0, dp) + (k + 1) * (k + 1);
        /* case 2 */
        for (int p = start; p < end; p++) {
            if (boxes[p] == boxes[end]) {  // try all break points where its color is the same as the last box
                maxScore = Math.max(maxScore, dfs(boxes, start, p, k + 1, dp) + dfs(boxes, p + 1, end - 1, 0, dp));
            }
        }
        
        dp[start][end][k] = maxScore;
        return maxScore;
    }
}