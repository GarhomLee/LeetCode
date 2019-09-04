https://leetcode.com/problems/missing-ranges/

// 对比：228. Summary Ranges

// 思路：常规遍历。参考https://leetcode.com/problems/missing-ranges/discuss/50476/Accepted-Java-solution-with-explanation下用户@a-b-c的答案。
//         step0: 单独讨论当nums数组为空或长度为0的特殊情况。
//         step1: 如果lower比nums[0]小，那么需要额外加入一个range字符串。
//         step2: 遍历nums数组，如果相邻两个数字满足相差大于1，那么就需要用helper method来获得一个range字符串，
//                 并加入res列表。
//                 注意：由于可能出现Integer.MIN_VALUE和Integer.MAX_VALUE的情况，所以比较两个数字时需要先转成
//                 long类型，避免overflow。
//         step3: 遍历结束，如果末尾元素nums[len-1]比upper小，说明需要额外加入一个range字符串。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.边界条件错误：当nums数组为空或长度为0的特殊情况需要单独讨论。
//         2.数据溢出错误：当比较nums[i + 1]和nums[i]是否相差大于1时，如果用nums[i + 1] - nums[i] > 1判断，
//             那么当nums[i]为Integer.MIN_VALUE而nums[i + 1]为Integer.MAX_VALUE时，会导致数据超过32位
//             整型范围；如果用nums[i + 1] > nums[i] + 1判断，那么当nums[i]和nums[i + 1]都为Integer.MAX_VALUE
//             时，也会导致数据超过32位整型范围而溢出。因此，需要将数字转换成空间更大的long类型来比较。

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int len = nums.length;
        // {Mistake 1}
        if (nums == null || len == 0) {
            res.add(getRange(lower, upper));
            return res;
        }  // {Correction 1}
        
        if (lower < nums[0]) {
            res.add(getRange(lower, nums[0] - 1));
        }
        
        for (int i = 0; i < nums.length - 1; i++) {
            // {Mistake 2}
            long num1 = nums[i], num2 = nums[i + 1];  // {Correction 2}
            if (num2 > num1 + 1) {
                res.add(getRange(num1 + 1, num2 - 1));
            }
        }
        
        if (nums[len - 1] < upper) {
            res.add(getRange(nums[len - 1] + 1, upper));
        }
        
        return res;
    }
    
    private String getRange(long num1, long num2) {
        return num1 == num2 ? num1 + "" : num1 + "->" + num2;
    }
}