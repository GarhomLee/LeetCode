https://leetcode.com/problems/stepping-numbers/

// 思路：DFS (backtracking)
//         将[1:9]作为递归函数的起始值，调用递归函数。如果【low==0，单独将0加入res】。
//         递归函数定义：void dfs(long curr, int low, int high)，表示搜索以curr为起始数位的所有在范围[low:high]里
//             的stepping numbers.
//         终止条件：由于curr是不断增加数位的，所以当curr > high，后续的搜索一定会比high大，因此直接返回。
//         递归过程：Goal：先检查curr，如果curr >= low，因为已经确定curr <= high，所以curr符合题意，加入res。
//                 Choices：得到curr的末位的数last，根据题意，下一位就是last+1或者last-1.
//                 Constraints：如果last为0，那么last-1是无效的，所以只递归last+1。同理，如果last为9，那么last+1是
//                     无效的，所以只递归last-1。如果last是[1:8]，那么两个都可以递归。
//         递归结束后，先排序然后返回res。
// 时间复杂度：O(2^(num of digit in high + 1))=O(2 ^(log10(high) + 1))
// 空间复杂度：O(??)
// 犯错点：1.题意理解错误：stepping number不是相邻数位严格递增或严格递减的数，而是只要相邻数位的值相差1即可，如67676。
//         2.起始条件错误：不能将0也作为递归函数起始值，因为在递归函数里会先将当前值curr*10，增加数位。但对于0来说，乘以10
//             并不能增加数位，因此会造成结果里有重复的[1:9]。

class Solution {
    List<Integer> res = new ArrayList<>();
    
    public List<Integer> countSteppingNumbers(int low, int high) {
        //for (int i = 0; i < 10; i++)  // {Mistake 2}
        for (int i = 1; i < 10; i++) {
            dfs(i, low, high);

        }
        if (low == 0) {  // {Correction 2}
            res.add(0);
        }
        
        Collections.sort(res);
        
        return res;
    }
    
    private void dfs(long curr, int low, int high) {
        if (curr > high) {
            return;
        }
        
        if (curr >= low) {
            res.add((int) curr);
        }
        
        long last = curr % 10;
        
        if (last > 0) {
            dfs(curr * 10 + (last - 1), low, high);
        }
        if (last < 9) {
            dfs(curr * 10 + (last + 1), low, high);
        }
    }
}