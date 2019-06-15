https://leetcode.com/problems/single-number-iii/

视频讲解：https://www.youtube.com/watch?v=kOMJAZ0t_F4
// 思路：Bit Manipulation。关键是将和两个不同single number相关的数合理的放到同一组，然后对同一组数做异或运算。
//      如果将所有数做异或运算，结果实际上等同于只将那两个single number做异或运算。因此，结果的二进制表示出现1的
//      位置必然为两个single number的不同之处，把这个结果记为diff。
//      实际上，不需要利用diff中所有的1，只需要利用其中一个1就可以作为分离因子区分两个single number。因此，只需要
//      用diff & (-diff)取最低位的那个1，作为分离因子，记为lowBit。
//      利用这个分离因子，可以把除了两个single number外的数分为两组，和lowBit的1位相同也为1的为一组，在lowBit的1位
//      为0的为另外一组。可以知道，两个single number也必然分别在其中一组。因此，把这两个组分别做异或运算，最后得到的
//      两个结果就是那两个single number。
时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums.length == 2) return nums;
        
        int diff = 0;  // record the difference of two single numbers
        for (int n: nums) {
            diff ^= n;
        }
        
        int lowBit = diff & (-diff);  // get the lowest 1 bit as differentiation factor
        int single0 = 0;  // 0 bit at position where lowBit has 1
        int single1 = 0;  // 1 bit at position where lowBit has 1
        for (int n: nums) {
            if ((n & lowBit) == 0) {  // 0 bit at position where lowBit has 1
                single0 ^= n;
            } else {  // 1 bit at position where lowBit has 1
                single1 ^= n;
            }
        }
        
        return new int[]{single0, single1};
    }
}