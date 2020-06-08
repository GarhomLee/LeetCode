https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/

idea: Sort
    -Transform the question to whether two arrays are containing the same elements.
time complexity: O(n log n)
space complexity: O(n) for sorting

class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}