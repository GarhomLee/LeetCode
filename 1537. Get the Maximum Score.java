https://leetcode.com/problems/get-the-maximum-score/

solution1: BinarySearch + DP
    -Split nums1 to subarray sum at the point where the num exists in both arr, and store in a list.
     Split nums2 in the same way in another list.
    -Since it can switch array at the point of common num, just pick the max at every position comparing two lists.
time complexity: O(m log n + n log m)
space complexity: O(m + n)

class Solution {
    final long MOD = 1_000_000_007;
    
    private List<Long> getSumList(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        long sum = 0;   // use long to avoid overflow
        List<Long> ret = new ArrayList<>();
        for (int i = 0; i < len1; i++) {
            sum += nums1[i];
            if (Arrays.binarySearch(nums2, nums1[i]) >= 0) {
                ret.add(sum);
                sum = 0;   
            }
        }
        ret.add(sum);
        return ret;
    }
    
    public int maxSum(int[] nums1, int[] nums2) {
        List<Long> list1 = getSumList(nums1, nums2), list2 = getSumList(nums2, nums1);
        long max = 0;
        for (int i = 0; i < list1.size(); i++) {
            max += Math.max(list1.get(i), list2.get(i));
        }
        
        return (int) (max % MOD);
    }
}


solution2: Two pointers + DP. Referring to: https://leetcode.com/problems/get-the-maximum-score/discuss/767987/JavaC%2B%2BPython-Two-Pointers-O(1)-Space
time complexity: O(m + n)
space complexity: O(1)