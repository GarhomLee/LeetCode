https://leetcode.com/problems/can-i-win/

// 思路：Minimax + Recursion with Memoization，视频讲解：https://www.youtube.com/watch?v=GNZIAbf0gT0
//         边界条件：如果可选择的范围maxChoosableInteger比目标desiredTotal大，那么一定能赢，返回true；
//                 如果可选择的范围中所有数的总和sum比目标desiredTotal还小，那么一定没办法应，返回false。
//         维护used数组，index考虑二进制，每一位bit表示maxChoosableInteger的每一个数是否被使用，如0000表示[1:4]都没有被使用，
//         0101表示[1:4]中1和3已经被使用了；value取值0，1，-1，其中0表示这个状态没有被查询过，1表示在这个状态下可以胜出，
//         -1表示在这个状态下会失败。
//         递归求解结果。
//         递归函数定义：dfs(int m, int t, int[] used, int state)，表示给定可选范围m，部分结果的情况used数组，求出能否在
//                 给定状态state的情况下得到总和超过t来赢得游戏。
//         终止条件：1) t <= 0，上一个人已经达到了目标，因此这一轮一定输，返回false
//                 2）used[state] != 0，表示状态state已经求解过结果了，直接判断used[state]是否等于1
//         递归过程：对于每一个[0:m)的取值num，如果state中对应位置的bit是1，表示已经被用过了，跳过；否则，递归调用dfs，
//                 将t更新为t-(num+1)，state更新为state | (1 << num)。
//                 如果在某一个递归结果中，下一轮的对手不能赢，那么这一轮我们就能赢，也就是说【只要遇到相同情况，我们都会作出
//                 同样的选择来保证自己赢】。因此，used[state]记录为1，并返回结果true。
//                 如果在所有[0:m)的取值中，下一轮都能赢，即这一轮都没有办法赢，说明在当前状态下是不可能赢的，因此，
//                 used[state]记录为-1，并返回结果false。
// 关键点：状态的记录，利用bit的0和1的状态来表示是否已经用过了某个数。
//         这是因为题目数据范围最大到20，因此可以利用32位的整型来记录状态。
// 犯错点：1.细节错误：bit操作错误，state & (1 << num)的结果不一定是1，而是要么0要么!0。只有进行右移操作，即state >> num，
//         结果才是0或1.

class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        /* corner cases */
        if (maxChoosableInteger >= desiredTotal) return true;
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;  // no possible soulution
        
        int[] used = new int[1 << maxChoosableInteger];
        return dfs(maxChoosableInteger, desiredTotal, used, 0);
    }
    
    private boolean dfs(int m, int t, int[] used, int state) {
        /* base cases */
        if (t <= 0) return false;
        if (used[state] != 0) return used[state] == 1;
        
        for (int num = 0; num < m; num++) {
            //if ((state & (1 << num)) == 1) continue;  // {Mistake 1}
            if ((state & (1 << num)) != 0) continue;  // {Correction  1}
            
            if (!dfs(m, t - (num + 1), used, state | (1 << num))) {
                used[state] = 1;
                return true;
            }
        }
        
        used[state] = -1;
        return false;
    }
}