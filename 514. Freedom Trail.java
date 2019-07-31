https://leetcode.com/problems/freedom-trail/

// 思路：Recursion with memoization。参考：https://leetcode.com/problems/freedom-trail/discuss/98953/JAVA-DP-with-explanation
//         维护二维的辅助数组dp，dp[rIndex][kIndex]表示从ring[rIndex]起始，拨完所有key[kIndex:length]
//         所需的最小步数。根据这个定义，【结果返回的是dp[0][0]】，再加上key.length()表示按按钮的次数。
//         递归函数定义：int dfs(String ring, int rIndex, String key, int kIndex, Integer[][] dp)
//                 表示从ring[rIndex]起始，拨完所有key[kIndex:length]所需的最小步数。
//         终止条件：1）kIndex == key.length()，所有key都拨完，返回0
//                 2）dp[rIndex][kIndex] != null，表示当前状态已经搜索过，直接返回dp[rIndex][kIndex]
//         递归过程：当前要拨的字母是key[kIndex]，那么需要遍历搜索ring中所有对应的字母，比较它们所能得到的结果，
//                 然后取最小值。
//                 ring中的当前字母位置为rIndex，记需要拨到的字母key[kIndex]在ring中对应的位置为nextIndex，
//                 那么从rIndex到nextIndex有两种转拨盘方式：顺时针，逆时针，分别记录所需步数，记为currStep。
//                 调用递归函数，求出从从ring[nextIndex]起始，拨完所有key[kIndex+1:length]所需的最小步数，
//                 记为nextStep。因此，minStep=currStep+nextStep。
//                 对于所有不同的nextIndex，求出对应的minStep，最终minStep取它们的最小值，并保存在dp[rIndex][kIndex]。
// 时间复杂度：O(r*k), r=ring.length(), k=key.length()
// 空间复杂度：O(r*k), r=ring.length(), k=key.length()
// 犯错点：1.Java内置函数使用错误：用String.indexOf()查找下一个相同的char时，起始位置应该为当前index+1，
//             否则仍然会返回当前index，没有意义。

class Solution {
    public int findRotateSteps(String ring, String key) {
        Integer[][] dp = new Integer[ring.length()][key.length()];
        return dfs(ring, 0, key, 0, dp) + key.length();  // min steps plus steps to push the button
    }
    
    private int dfs(String ring, int rIndex, String key, int kIndex, Integer[][] dp) {
        /* base case */
        if (kIndex == key.length()) {  // done searching
            return 0;
        }
        if (dp[rIndex][kIndex] != null) return dp[rIndex][kIndex];  // use memoizaiton
        
        int minStep = Integer.MAX_VALUE;
        char c = key.charAt(kIndex);
        int nextIndex = ring.indexOf(c);  // get the index of char in ring where it matches the same char of key to dial
        
        while (nextIndex >= 0) {  // search for the same char to dial in String ring
            int currStep = Math.min(Math.abs(nextIndex - rIndex), ring.length() - Math.abs(nextIndex - rIndex));
            int nextStep = dfs(ring, nextIndex, key, kIndex + 1, dp);
            
            minStep = Math.min(minStep, currStep + nextStep);
            //nextIndex = ring.indexOf(c, nextIndex);  // {Mistake 1}
            nextIndex = ring.indexOf(c, nextIndex + 1);  // get the next same char
                                                         // {Correction 1}
        }
                
        dp[rIndex][kIndex] = minStep;  // set memoization
        return minStep;
    }
}