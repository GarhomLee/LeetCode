https://leetcode.com/problems/patching-array/

// 思路：Greedy，详见：https://leetcode.com/problems/patching-array/discuss/78488/Solution-%2B-explanation
//         维护变量missNum，表示需要插入的最小元素。观察可以发现，【当前条件下能覆盖到的sum的范围是[1:missNum)】，
//         左闭右开。如果插入missNum，那么【新增的覆盖范围是[missNum:missNum*2)】，因此【总的覆盖范围变成了
//         [1:missNum*2)】。
//         因此，关键点在于【比较当前nums[i]和missNum的大小关系】。
//         有两种可能情况：
//         1）missNum >= nums[i]，说明当前条件下能覆盖到的sum的范围[1:missNum)已经覆盖了nums[i]。如果将
//             [1:missNum)每个数都加上nums[i]，能覆盖的范围是[nums[i]:nums[i]+missNum)。取并集，总的
//             覆盖范围变为[1:nums[i]+missNum)
//         2）missNum < nums[i]，如果将[1:missNum)每个数都加上nums[i]，中间的范围[missNum:nums[i])并不能被覆盖，
//             因此需要将missNum插入，将覆盖范围变为[1:missNum*2)（即[1:missNum)和[missNum:missNum*2)的并集），
//             同时更新count++，更新missNum = missNum * 2.
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.数据范围错误：当n给到Integer.MAX_VALUE时，要达到missNum>n的循环终止条件，missNum必须为long类型。
//         2.循环条件错误：missNum是最小的需要插入的元素，目前能覆盖的sum的范围是[1:missNum)。如果以missNum>=n
//                 作为循环终止条件，那么当missNum==n时，就会退出循环，但实际上这时候sum的范围只是[1:n)，
//                 并不能取到n。因此，循环终止条件应该是missNum>n，即循环条件为missNum <= n。

class Solution {
    public int minPatches(int[] nums, int n) {
        if (n <= 0) return 0;
        //int missNum = 1;  // {Mistake 1}
        long missNum = 1;  // {Correction 1}
        int count = 0, i = 0;
        //while (missNum < n)  // {Mistake 2}
        while (missNum <= n) {  // {Correction 2}
            if (i < nums.length && missNum >= nums[i]) {
                missNum += nums[i++];
            } else {
                count++;
                missNum = missNum << 1;
            }
        }
        
        return count;
    }
}