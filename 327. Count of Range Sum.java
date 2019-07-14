https://leetcode.com/problems/count-of-range-sum/

// 解法一：TreeMap
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)


// 解法二：Recursion + Divide & Conquer (Modified Merge Sort)，利用Merge Sort半排序的特性来解决问题
//        首先，得到所有nums[0:i)的前缀和sums[i]。然后，调用递归函数得到sums[0:sums.length-1]中任意i<j
//        有lower<=sums[j]-sums[i]<=upper的{i,j}配对数。涉及配对，可以考虑Merge Sort。
//        递归函数定义：count(int[] sums, int low, int high)，返回sums[low:high]中任意i<j
//             有lower<=sums[j]-sums[i]<=upper的{i,j}配对数。
//        终止条件：low >= high，不可能形成配对，返回0
//        递归过程：先从中间位置mid将sums[low:high]分成左右两半，递归调用分别求出i，j同时在sums[low:mid]和
//                 i，j同时在sums[mid+1:high]的合法{i,j}配对数，然后再求i，j分别在左右半边的合法{i,j}配对数。
//                 left指针扫描每个左半边sums[low:mid]的元素，右边有两个指针：rightLower找到第一个使得
//                 sums[rightLower] - sums[left] >= lower的位置，rightUpper找到第一个使得
//                 sums[rightUpper] - sums[left] > upper的位置。因为右半边已经排好序，因此[rightLower:rightUpper)
//                 的所有元素sums[right]都满足lower<=sums[right]-sums[left]<=upper。
//                 由于左半边也已经排好序，更新left++后，对应的rightLower一定不会在前一个rightLower左边，对
//                 于rightUpper也同理，因此【不需要对于每个left都重置rightLower和rightUpper从mid+1开始，只需要
//                 一直向后扫描即可】。
//                 处理完所有配对后，将sums[low:mid]排序（O(n log n)，可以优化为O(n)）。
// 关键理解点：当求出前缀和数组sums后，sums[i]就可以变换位置了，和nums[0:i)实际上没有任何关系，只要在当前状态下
//         保持着对于任意i<j有lower<=sums[j]-sums[i]<=upper的相对位置关系。
//         对于任意i，在Merge Sort过程中，利用半排序的性质，就可以不断得到位于i右边的能形成合法配对的j。
// 时间复杂度：O(n (logn)^2)，排序的过程可以进一步优化
// 空间复杂度：O(n)
// 犯错点：1.数据溢出错误：如果nums数组里有连续Integer.MAX_VALUE，那么int[]不可能存得了，要用long[]
//        2.边界条件错误：如果low == high时提前判断lower <= sums[low] <= upper，那么并入到[0:i]里时，实际上
//          sums[i] - sums[0]还会再计算一次，重复计算导致结果出错。因此，单独的sums[low]不要考虑，只考虑能配对时
//          的情况。
//        3.细节错误：得到的范围[rightLower:rightUpper)是左闭右开，因此求个数只要rightUpper - rightLower。

class Solution {
    int lower, upper;
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        //int[] sums = new int[nums.length + 1];  // {Mistake 1}
        long[] sums = new long[nums.length + 1];  // {Correction 1}
        /* get the prefix sum */
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        return count(sums, 0, sums.length - 1);
    }
    
    private int count(int[] sums, int low, int high) {
        /* base case */
        if (low >= high) return 0;  // {Correction 2}
        //if (low > high) return 0;  // {Mistake 2}
        //if (low == high) return sums[low] >= lower && sums[low] <= upper ? 1 : 0;  // {Mistake 2}
        
        int mid = low + (high - low) / 2;
        int count = count(sums, low, mid) + count(sums, mid + 1, high);
        
        for (int left = low, rightLower = mid + 1, rightUpper = mid + 1; left <= mid; left++) {
            /* find the first index where sums[rightLower] - sums[left] >= lower */
            while (rightLower <= high && sums[rightLower] - sums[left] < lower) {
                rightLower++;
            }
            /* find the first index where sums[rightUpper] - sums[left] > upper */
            while (rightUpper <= high && sums[rightUpper] - sums[left] <= upper) {
                rightUpper++;
            }

            //count += rightUpper - rightLower + 1;  // {Mistake 3}
            count += rightUpper - rightLower;  // {Correction 3}
        }
        Arrays.sort(sums, low, high + 1);
        
        return count;
    }
}