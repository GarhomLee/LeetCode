https://leetcode.com/problems/continuous-subarray-sum/

// 解法一：常规遍历
//         维护sums数组，前缀和sums[i]表示nums[0:i)的和。
//         对于每个sums[i]，都去求一遍与sum[0:i-2]的差值，然后进行逻辑判断，符合条件返回true。如果遍历结束，都没有
//         任何符合条件的，返回false。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
// 犯错点：1.边界条件：nums元素个数小于2，直接返回false；如果k==0，且存在两个数以上和为0，可以返回true；
//         或者k可以作为被除数，这时k要求不等于0，可以整除时返回true

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        /* base case */
        if (nums.length < 2) return false;

        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
            for (int j = 0; j <= i - 2; j++) {
                //if ((sums[i] - sums[j]) % k == 0) return true;  // {Mistake 1}
                if ((k == 0 && sums[i] - sums[j] == 0) || (k != 0 && (sums[i] - sums[j]) % k == 0)) {  // {Correction 1}
                    return true;
                }
            }
        }
        return false;
    }
}


// 解法二：HashMap + 数学
//         HashMap中，key为nums[0:i]的前缀和跟k进行模运算后的【余数】，value为余数第一次出现的位置index。
//         初始化map.put(0, -1);
//         遍历nums数组，【对于k!=0的情况模运算求余数】，然后从Map里找该余数是否已经出现过，且出现的位置相隔大于2.
//         将第一次出现的余数加入Map中。
// 关键点：1.为什么要用余数？这是因为要将不同的倍数统一成同一种情况，方便利用Map查询。详细数学证明见：
//         https://leetcode.com/problems/continuous-subarray-sum/discuss/150330/Math-behind-the-solutions
//         2.对于k==0的情况，不需要求余数
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.细节错误：如果直接用put()而不是putIfAbsent()，那么可能会导致最开始的key-value pair被覆盖掉

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        /* base case */
        if (nums.length < 2) return false;

        Map<Integer, Integer> map = new HashMap();
        map.put(0, -1);
        int mod = 0;
        for (int i = 0; i < nums.length; i++) {
            mod += nums[i];
            if (k != 0) mod %= k; 
            if (map.containsKey(mod) && i - map.get(mod) >= 2) {
                return true;
            }
            //map.put(mod, i);  // {Mistake 1}
            map.putIfAbsent(mod, i);  // {Correction1 1}
        }
        
        return false;
    }
}