https://leetcode.com/problems/sequential-digits/

// 解法一：DFS
//     递归函数定义：void dfs(int curr, int low, int high)，表示找到所有从curr开始的，处于[low:high]范围内
//         的sequential digits。
//     终止条件：curr > high，再继续加位数搜索一定更大；或curr % 10 == 0，说明从结尾为9的数字增加而来，不能再增加。
//     递归过程：先判断当前数字curr是否在[low:high]范围内，如果是则加入res。
//             调用dfs()，将curr更新为curr * 10 + curr % 10 + 1。
// 时间复杂度：O(log high)
// 空间复杂度：O(log high)

class Solution {
    List<Integer> res = new ArrayList<>();
    
    private void dfs(int curr, int low, int high) {
        if (curr > high || curr % 10 == 0) return;
        
        if (curr >= low) {
            res.add(curr);
        }
        
        dfs(curr * 10 + curr % 10 + 1, low, high);
    }
    
    public List<Integer> sequentialDigits(int low, int high) {
        int count = 0, startInt = 0;
        int temp = low;
        while (temp != 0) {
            count++;
            startInt = temp % 10;
            temp /= 10;
        }
        
        // edge case
        if (count > 9) {
            return res;
        }
        
        // initialize
        int startNum = 0, increment = 0;
        for (int i = 0; i < count; i++) {
            startNum = startNum * 10 + (i + 1);
            increment = increment * 10 + 1;
        }
        for (int i = 0; i < 10 - count; i++) {
            dfs(startNum, low, high);
            startNum += increment;
        }
        
        Collections.sort(res);
        return res;
    }
}


解法二：BFS