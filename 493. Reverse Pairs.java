https://leetcode.com/problems/reverse-pairs/

// 思路：Recursion + Divide & Conquer (Modified Merge Sort)，利用Merge Sort半排序的特性来解决问题
//      视频讲解：https://www.youtube.com/watch?v=j68OXAMlTM4
//      递归函数定义：count(int[] nums, int low, int high)，返回nums[low:high]里的reverse pairs
//      终止条件：low >= high，不可能形成pair，返回0
//      递归过程：从中间位置mid将nums[low:high]分为左右两半。虽然reverse pairs可能同时在左半边、同时在
//             右半边、一个在左边一个在右边，但前两种情况可以通过递归调用解决，因此只需要处理一个在左边一个
//             在右边的情况。
//             先将nums[low:mid+1)和nums[mid+1:high+1)排序。利用两个指针left和right，分别指向左半元素
//             和右半元素，会出现两种情况：
//             1）nums[left] > nums[right] * 2，符合题意。实际上，由于已经排好序，所有[left:mid]的元素都
//                符合题意，可组成mid-left+1对。这时要更新right++。
//             2）nums[left] <= nums[right] * 2，不符合题意。由于已经排好序，所有[right:high]都比left大，
//                因此要更新left++来寻求新的解。
// 注意：在判断比较nums[left] > nums[right] * 2时，为了防止数据溢出，要转换成long
// 犯错点：1.Java语言特性错误：Arrays.sort()如果给定范围，应该是左闭右开的。
//        2.细节错误：left从low指针开始，而不是从0开始。

class Solution {
    public int reversePairs(int[] nums) {
        return count(nums, 0, nums.length - 1);
    }
    
    private int count(int[] nums, int low, int high) {
        if (low >= high) return 0;
        
        int mid = low + (high - low) / 2;
        int res = count(nums, low, mid) + count(nums, mid + 1, high);
        Arrays.sort(nums, low, mid + 1);
        //Arrays.sort(nums, mid + 1, high);  // {Mistake 1}
        Arrays.sort(nums, mid + 1, high + 1);
        //int left = 0;  // {Mistake 2}
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            if ((long) nums[left] > (long) nums[right] * 2) {
                res += mid - left + 1;
                right++;
            } else {
                left++;
            }
        }
        
        return res;
    }
}