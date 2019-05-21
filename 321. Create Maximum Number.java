https://leetcode.com/problems/create-maximum-number/

// 总体思路：比较复杂的一道难度为Hard的题。大体上可以分为两步：
//         1）从nums1和nums2各取一部份subarray，长度分别为i和k-i，合计共取k个数。
//         2）将两个subarray合并成长度为k的maximum array。不同的i对应不同的取法，最后结果为所有可能取法中最大的maximum array。
//         这两步都需要用到greedy method的思想。第一步的greedy体现在取的subarray必定为相同长度中所能取的最大maximum subarray，这样
//         在下一步merge后才能保证得到maximum array。
//         在第一步中，维持当前的nums[i]在res数组中是末尾的元素，即res[0:j-1]都不比nums[i]小，所以需要删掉res数组末尾比nums[i]小的元素，
//         或nums[i]来到了res[0]，或由于nums数组剩下的元素个数正好能填充res数组的剩下位置，不能再删除更多元素。
// 时间复杂度：O(k * (n1+n2)^2)
// 空间复杂度：O(n1+n2)

class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxNum = new int[k];
        if (k == 0 || k > nums1.length + nums2.length) return maxNum;  // corner cases
        
        for (int i = Math.max(0, k - nums2.length); i <= Math.min(nums1.length, k); i++) {  // the start position of i should guarantee that k-i is at most nums2.length
            int[] array1 = findMaxArray(nums1, i);  // find the maximum subarray by greedy method
            int[] array2 = findMaxArray(nums2, k - i);
            maxNum = max(maxNum, 0, mergeMax(array1, array2), 0);  // merge two subarray into maximum array with length k
        }
        return maxNum;
    }
    
    /** given an int array and a number n, find the subarray of maximum number with the length n */
    private int[] findMaxArray(int[] nums, int k) {
        if (k == 0) return new int[0];
        if (k == nums.length) return nums;
        
        int[] res = new int[k];
        int j = 0;  // j indicates the position in res array where the next nums[i] can be added, and the maximum value of j is res.length
        for (int i = 0; i < nums.length; i++) {
            while (j > 0 && res[j - 1] < nums[i] && nums.length - i > k - j) {  // {Mistake1: nums.length - i > k - j guarantee that the rest of nums array is sufficient to add into res array, thus we can still pop elements from res array} {Correction 1}
                j--;  // pop the tail element of res array until it is greater than or equal to nums[i]
            }
            
            if (j < res.length) res[j++] = nums[i];  // guarantee that it will not go out of the boundary
        }
        return res;
    }
    
    /** given two int arrays, merge them into a new array such that it is the maximum array */
    private int[] mergeMax(int[] nums1, int[] nums2) {
        if (nums1.length == 0) return nums2;
        if (nums2.length == 0) return nums1;
        
        int[] res = new int[nums1.length + nums2.length];
        int s1 = 0, s2 = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = max(nums1, s1, nums2, s2) == nums1 ? nums1[s1++] : nums2[s2++];  // pick the head element from the "bigger" array by comparison
        }
        return res;
    }
    
    /** given two int arrays, determine which one has the maximum number starting from a specific position. Similar to String comparison*/
    private int[] max(int[] nums1, int s1, int[] nums2, int s2) {  // optimization: indicate specific start position
        int i = s1, j = s2;
        while (i < nums1.length || j < nums2.length) {
            if (i == nums1.length) return nums2;
            else if (j == nums2.length) return nums1;
            else if (nums1[i] > nums2[j]) return nums1;
            else if (nums1[i] < nums2[j]) return nums2;
            i++;
            j++;
        }
        return nums1;  // when nums1 equals to nums2
    }
}