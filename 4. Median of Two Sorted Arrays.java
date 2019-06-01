https://leetcode.com/problems/median-of-two-sorted-arrays/

// 总体思路：利用两个数组都已经排好序的性质，以及给定长度的数组中位数位置必然确定的性质，用Binary Search找到中位数。
//         如果把两个数组合并并排好序，中位数必然在合并后的数组的中间，意味着比中位数小的数（左半边数组）必然来自nums1数组较小的半边和nums2数组较小的半边.
//         令来自nums1数组的元素个数为mid1，来自nums1数组的元素个数为mid2（mid1和mid2都可以取0但不会同时取0），那么mid1+mid2为（左）中位数的位置，
//         等于(len1 + len2 + 1) / 2，这里+1是为了当总元素个数为奇数时取到最中间的数。同时，如果mid1确定，那么mid2也随之确定。
//         nums1数组左边的mid1个元素的最右边元素为left1，下一个元素（即右边len1-mid1个元素的最左边元素）为right1，nums2数组同理。
//         根据排好序的性质，left1<=right1，left2<=right2。如果有left1<=right2且left2<=right1，说明nums1前mid1个元素和nums2前mid2个元素集合全都小于等于
//         nums1数组和nums2数组右半边元素集合，那么中位数位置就找到了，为max(nums1[mid1 - 1],nums2[mid2 - 1])。如果总元素个数为偶数，还需要求min(nums1[mid1],nums2[mid2])，
//         然后取平均。
//         如果left1 > right2，说明nums1数组左半边元素过多，需要将mid1指针左移。否则，将mid1指针右移，增加nums1数组左半边元素个数。
//         注意：因为mid1指的是元素个数，可能为0，这时nums1数组左半边元素个数为0，left1需要赋值为Integer.MIN_VALUE。当mid1 == len1，nums1数组右半边元素个数为0，right1需要赋值
//         为Integer.MAX_VALUE。nums2数组的mid2同理。
// 时间复杂度：O(log(min(m,n))), m=nums1.length, n=nums2.length
// 空间复杂度：O(1)
// 犯错点：1.如果总长度是偶数，那么中数是两个中间数的平均，需要先转成double再取平均，否则如果是小数会取下整导致出错

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return  findMedianSortedArrays(nums2, nums1);  // optimization
        int len1 = nums1.length, len2 = nums2.length;
        int low = 0, high = len1;  // two pointers in nums1 array
        while (low <= high) {  // equal sign should be included
            int mid1 = low + (high - low) / 2;  // partition nums1 array with left part of mid1 elements
            int mid2 = (len1 + len2 + 1) / 2 - mid1;  // partition nums2 array with left part of mid2 elements
                                                    // in total there are (mid1 + mid2) elements in the left part of the merged array
            
            int left1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];  // left part of nums1 array with mid1 elements, and the index of mid1-th element is mid1 - 1 which is the right most element in the left part
            int right1 = mid1 == len1 ? Integer.MAX_VALUE : nums1[mid1];  // right part of nums1 array with (len1 - mid1) elements, and the index of (mid1 + 1)-th element is mid1 which is the left most element in the right part
            
            int left2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];  // left part of nums2 array with mid2 elements, and the index of mid2-th element is mid2 - 1 which is the right most element in the left part
            int right2 = mid2 == len2 ? Integer.MAX_VALUE : nums2[mid2];  // right part of nums2 array with (len2 - mid2) elements, and the index of (mid2 + 1)-th element is mid1 which is the left most element in the right part
            
            if (left1 <= right2 && left2 <= right1) {  // the elements in both the left part of nums1 and the left part of nums2 are all less than or equals to the right part of nums1 and the right part of nums2, which indicates the position where median appears has found
                if ((len1 + len2) % 2 == 1) return Math.max(left1, left2);
                //else return (Math.max(left1, left2) + Math.min(right1, right2)) / 2;  // {Mistake 1} 
                else return ((double) Math.max(left1, left2) + Math.min(right1, right2)) / 2;  // {Correction 1: since this function should reture doulbe value, we should convert int to double before taking average of two numbers}
            } else if (left1 > right2) {  // we should reduce the number of elements in the left part of nums1 so that to increase those in the left part of nums2
                high = mid1 - 1;
            } else low = mid1 + 1;
        }
        return Double.MIN_VALUE;
    }
}