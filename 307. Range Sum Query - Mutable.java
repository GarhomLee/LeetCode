https://leetcode.com/problems/range-sum-query-mutable/

// 解法一：Naive memoization
// 时间复杂度：update: O(n); sumRange: O(1)
// 空间复杂度：O(n)


// 解法二：Binary Indexed Tree
//        构建class BIT，内部以数组形式实现，维护以下性质：
//        1）1-based，sums[i]表示[1:i]的partial sum
//        同时，实现以下功能：
//        1）update(int i, int delta)，从第i个元素开始的partial sum都增加delta
//        2）sum(int i)，求nums[0:i-1]的和（不是partial sum）
//        3）lowBit(int i)，得到i的最后一位1bit
//        因此，外层class NumArray只需要调用class BIT。
// 时间复杂度：update: O(log n); sumRange: O(log n)
// 空间复杂度：O(n)
// 犯错点：1.给BIT传delta的时候，delta是val和最新nums[i]的差值，而不是和bit.sum(i + 1)的差值。所以需要维护一个nums的copy。
//        2.在BIT的constructor里，要用update把内部的sums数组构建好

class NumArray {
    BIT bit;
    int[] copy;
    
    public NumArray(int[] nums) {
        bit = new BIT(nums);
        copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
    }
    
    public void update(int i, int val) {
        //int delta = val - bit.sum(i + 1);  // {Mistake 1: bit.sum(i + 1) is the partial sum of nums[0:i]}
        int delta = val - copy[i];
        copy[i] = val;
        bit.update(i + 1, delta);
    }
    
    public int sumRange(int i, int j) {
        return bit.sum(j + 1) - bit.sum(i);
    }
    
    /* Binary Indexed Tree, 1-based */
    class BIT {
        int[] sums;
        public BIT(int[] nums) {
            sums = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {  // {Mistake 2} {Correction 2}
                update(i, nums[i - 1]);
            }
        }
        /** update all partial sums from index i */
        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += lowBit(i);
            }
        }
        /** get the sum of [1:i] */
        public int sum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += sums[i];
                i -= lowBit(i);
            }
            return sum;
        }
        public int lowBit(int i) {
            return i & (-i);
        }
    }
}