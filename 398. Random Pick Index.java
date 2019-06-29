https://leetcode.com/problems/random-pick-index/

// 思路：HashMap
// 出现的问题：题目对于空间复杂度有要求，只能使用constant extra space


// 思路：Reservoir Sampling
//      遍历数组，如果遇到了target，记录个数count，然后“抽奖”，以如果1 / count的概率抽到0，那么也能以同样概率抽到当前index，
//      那么需要更新index。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    int[] copy;
    Random random;
    
    public Solution(int[] nums) {
        copy = nums;
        random = new Random();
    }
    
    public int pick(int target) {
        int count = 0, index = -1;
        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != target) continue;
            count++;
            if (random.nextInt(count) == 0) {
                index = i;
            }
        }
        return index;
    }
}