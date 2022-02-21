https://leetcode.com/problems/merge-sorted-array/

idea: Two (Three?) Pointers
time comp: O(m+n)
space comp: O(1)

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int right = m + n - 1;
        m--;
        n--;        
        while (n >= 0 && right >= 0) {
            if (m >= 0 && nums1[m] >= nums2[n]) {                
                nums1[right] = nums1[m];
                right--;
                m--;
            } else {
                nums1[right] = nums2[n];
                right--;
                n--;
            }
        }
    }
}