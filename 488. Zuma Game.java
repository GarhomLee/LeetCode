https://leetcode.com/problems/zuma-game/

// 思路：DFS (Recursion)。视频讲解：https://www.youtube.com/watch?v=KVlbMB7gRIk
//         首先，遍历hand，统计各个颜色球的个数。
//         然后，递归求解使得board清空的最小球数。
//         递归函数定义：dfs(String board, int[] handCount)，表示利用handCount里的球数，清空board里所有球所需的最少
//                 球数。返回最少球数，或者-1。board保证了【不会有连续超过3个或以上的同色球】。
//         终止条件：board.isEmpty()，表示已经清空，返回0
//         递归过程：维护变量ans，表示当前条件下清空当前board的最少球数，初始化为Integer.MAX_VALUE。
//                 维护两个指针：left指向连续同色球中的第一个球，right指向接下来的不同色球的第一个球。因此，right-left为
//                 连续同色球的个数，记为ballCount。
//                 判断handCount里可用的球数加上当前的同色球超过3个。如果是，进行以下操作：
//                 从handCount中减去用掉的球数，board利用helper method更新为加入球后消除所有可能的连续3个同色球后的board，
//                 然后递归调用dfs()，得到在更新后所需的球数temp。如果temp >= 0，说明有解，利用temp + 3 - ballCount更新
//                 ans。然后重新将球放回handCount。
//                 更新left = right。
//                 如果当前board尝试完所有结果后，ans仍然是Integer.MAX_VALUE没有更新，说明没有一个可行解，因此返回-1。否则返回ans。
// 小技巧：利用update()函数时，可以直接传入删除了当前颜色球的String，即使board中当前颜色球不足3个，这样不仅能简化代码，还能减少update()
//         中的delete()操作次数。
// 犯错点：1.细节错误：变量含义混淆了，ballCount指的是board上存在的连续同色球个数，3 - ballCount才是要放进去的球的个数，
//             temp + 3 - ballCount才是递归求得后续的最小球数 + 当前条件下要放的球数。
//         2.Java内置函数使用错误：StringBuilder的delete()能直接改变当前instance的内部char数组，不需要返回值。
//             不同于String的substring()，需要用新的String变量储存返回值，而instance本身不会改变。

class Solution {
    public int findMinStep(String board, String hand) {
        int[] handCount = new int[26];
        for (char c: hand.toCharArray()) {
            handCount[c - 'A']++;
        }
        return dfs(board, handCount);
    }
    
    private int dfs(String board, int[] handCount) {
        if (board.isEmpty()) return 0;
        
        int ans = Integer.MAX_VALUE;
        int left = 0;
        while (left < board.length()) {
            int right = left;
            char ball = board.charAt(left);
            while (right < board.length() && board.charAt(right) == ball) {
                right++;
            }
            int ballCount = right - left;  // indicate the number of contiguous balls with the same color
            /* if balls on the board plus in hand are greater than removal condition 3 */
            if (ballCount + handCount[ball - 'A'] >= 3) {
                handCount[ball - 'A'] -= 3 - ballCount;
                int temp = dfs(update(board.substring(0, left) + board.substring(right)), handCount);
                //if (temp >= 0) ans = Math.min(ans, temp + ballCount);  // {Mistake 1}
                if (temp >= 0) ans = Math.min(ans, temp + 3 - ballCount);  // {Correction 1: 3 - ballCount is the number of inserted balls}
                handCount[ball - 'A'] += 3 - ballCount;  // reset for backtracking
            }

            /* update left pointer */
            left = right;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private String update(String board) {
        if (board.isEmpty()) return board;
        StringBuilder sb = new StringBuilder(board);
        int left = 0;
        while (left < sb.length()) {
            int right = left;
            char ball = sb.charAt(left);
            while (right < sb.length() && sb.charAt(right) == ball) {
                right++;
            }
            if (right - left >= 3) {
                sb = sb.delete(left, right);
                left = 0;  // some balls in the middle have been removed, so start from the beginning
            } else {
                left = right;
            }
        }
        return sb.toString();
    }
}
