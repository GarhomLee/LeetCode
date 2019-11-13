https://leetcode.com/problems/n-repeated-element-in-size-2n-array/

// 思路：Hash Table
//         由于题目限制了只有1种元素会出现2次以上，且要找的就是这个元素，因此可以直接用HashSet来找重复元素。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int repeatedNTimes(int[] A) {
        int len = A.length, res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        
        return -1;
    }
}