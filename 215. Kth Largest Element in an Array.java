https://leetcode.com/problems/kth-largest-element-in-an-array/

// 解法一：Java偷懒解法：直接sort，然后取length - k的数。
// 时间复杂度：O(n log n)
// 空间复杂度：O(1)

// 解法二：minHeap
//         维护一个size为k的heap，当加入一个新的数时，将top即最小值删除，保证heap中k个数一直是局部最大，最终top元素为全局最大。
// 时间复杂度：O(n log k)
// 空间复杂度：O(k)

// 解法三：Quick Select
//     1）由于找【第k大】的数，所以把【较大的数放pivot左边，较小的数放pivot右边】
//     2）用helper method求出当前指定的pivot在排序后的位置pos。while (start <= end)：如果pos == k - 1，意味着nums[pos]左边有k - 1个比它大的数，
//         即nums[pos]是第k大的数，返回之。否则，如果pos < k - 1，那个数在其右边，start = pos + 1；如果pos  > k - 1，那个数在其左边，end = pos - 1
//     3）helper method中，以nums[start]作为pivot，left = start + 1，right = end。while (left <= right)【等号很重要！】：不断交换，使得left的左边
//         放的是大于pivot的数，right的右边放的是小于pivot的数，直至left crosses right。
//         接下来的重点是：pivot和left还是right交换？因为要保持pivot的左边都是大于pivot的数，而right的右边是小于pivot的数，意味着right指向第一个大于等于
//         pivot的数，符合要求，所以pivot要和【right】交换。
// 时间复杂度: average O(n); worse case O(n^2)
// 空间复杂度: O(1)
// 犯错点：1.left和right指针移动时，最好每次移动一个，这样可以保证left==right的情况也能得到处理，跳出循环时right指针一定指向大于pivot的数。
//             不要用while循环来移动，否则容易出错
//         2.拿pivot和nums[right]交换，因为当跳出循环时right指针一定指向大于pivot的数，而pivot是nums数组第right + 1大的数

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int pos = quickSelect(nums, start, end);  // use helper method to find the position of nums[start] where all elments on its left is >= nums[start],
                                                      // and all elments on its righg is <= nums[start]
            if (pos == k - 1) return nums[pos];  // proper position found
            else if (pos < k - 1) start = pos + 1;  // not enough elments on the left, so we should search its right part
            else end = pos - 1;  // too manh elments on the left, so we should search its left part
        }
        return Integer.MIN_VALUE;
    }
    
    /** find the proper position of element nums[start] by moving elements in nums[start+1:end] to proper half*/
    private int quickSelect(int[] nums, int start, int end) {
        if (start == end) return start;
        int pivot = nums[start];
        int left = start + 1, right = end;
        while (left <= right) {
            /*while (left < right && nums[left] >= pivot) left++;
            while (left < right && nums[right] <= pivot) right--;
            swap(nums, left++, right--);*/  // {Mistake 1}

            if (nums[left] >= pivot) left++;
            else if(nums[right] <= pivot) right--;
            else swap(nums, left++, right--);  // {Correction 1}
        }

        /*swap(nums, start, left - 1);
        return left - 1;*/  // {Mistake 2: technically this is not a mistake, but it creates a confusion}

        swap(nums, start, right);
        return right;  // {Correction 2: it guarantees that nums[right] > pivot, otherwise right pointer would move leftwards}
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

