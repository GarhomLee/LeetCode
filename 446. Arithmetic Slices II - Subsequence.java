https://leetcode.com/problems/arithmetic-slices-ii-subsequence/

// 对比：413. Arithmetic Slices，不同点在于本题组成subsequence的两个元素可以在数组A中不是紧密相邻。
// 思路：DP，视频讲解：https://www.youtube.com/watch?v=RaNUycEtGAk
//         先做预处理，利用HashMap，key为A数组中出现的所有数，value为这些数的index集合（列表）。
//         状态函数：dp[i][j]表示以A[i]为倒数第二个数，A[j]为倒数第一个数所能组成的arithmetic subsequence slices的个数。
//                 如果不能组成间隔相同的triplet，那么dp[i][j]=0
//         状态转移方程：用两层循环遍历可能的第二数位置secondIndex和第三数位置thirdIndex，然后求得第一数的值firstNum。
//                 如果HashMap中存在firstNum，那么从HashMap中取出它比secondIndex小的位置，的得到dp[firstIndex][secondIndex]。
//                 由于这三个数可以形成间隔相同的triplet（即arithmetic slices），所以对于所有的firstIndex，可以得到状态转移方程
//                 dp[secondIndex][thirdIndex] += dp[firstIndex][secondIndex] + 1。
//                 利用dp[secondIndex][thirdIndex]，更新最终结果res。最后返回res。
//         初始值：无特殊初始值，所有dp[i][j]=0
// 犯错点：1.数据范围错误：如果第三数和第二数的差超过了Integer的范围，那么得到的第一数用int表示不了。因此，需要将第一数
//             转换成long。与此相对应，HashMap中的key也要用Long表示。

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) return 0;
        
        int res = 0;
        /* record all unique numbers with their positions, there might be duplicates */
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.putIfAbsent((long) A[i], new ArrayList<>());
            map.get((long) A[i]).add(i);
        }
        
        int[][] dp = new int[A.length][A.length];
        for (int secondIndex = 1; secondIndex < A.length - 1; secondIndex++) {
            for (int thirdIndex = secondIndex + 1; thirdIndex < A.length; thirdIndex++) {
                //int firstNum = A[secondIndex] - (A[thirdIndex] - A[secondIndex]);  // {Mistake 1}
                long firstNum = (long) A[secondIndex] * 2 - A[thirdIndex];  // {Correction 1}
                if (!map.containsKey(firstNum)) continue;  // skip if the first number of the triplet is not in A array
                
                for (int firstIndex: map.get(firstNum)) {
                    if (firstIndex >= secondIndex) continue;
                    dp[secondIndex][thirdIndex] += dp[firstIndex][secondIndex] + 1;
                }
                
                res += dp[secondIndex][thirdIndex];
            }
        }
        
        return res;
    }
}
