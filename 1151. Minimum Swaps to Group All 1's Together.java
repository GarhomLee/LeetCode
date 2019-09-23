https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/

// 思路：sliding window
//         如果将所有1放到一起的长度为ones，那么题目可以转化为寻找窗口大小为ones的所有窗口中包含1最多的窗口，
//         而非1（也就是0）的个数就是最少的，且等于最小交换次数。
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.边界条件错误：当data数组中只有0没有1时，窗口左边缘ones - 1为-1，越界。因此，要判断这个特殊情况。
//         2.细节错误：最小的交换次数应该是窗口大小ones和窗口包含的最多的1的个数maxCount的差值，而不是整个data数组
//             长度和maxCount的差值。

class Solution {
    public int minSwaps(int[] data) {
        int ones = 0;
        for (int num: data) {
            ones += num;
        }
        
        // {Mistake 1}
        if (ones < 2) {
            return 0;
        }  // {Correction 1}
        
        int maxCount = 0, count = 0;
        for (int i = 0; i < data.length; i++) {
            count += data[i];
            maxCount = Math.max(maxCount, count);
            
            if (i >= ones - 1) {
                count -= data[i - ones + 1];
            }
        }
        
        //return data.length - maxCount;  // {Mistake 1}
        return ones - maxCount;  // {Correction 1}
    }
}