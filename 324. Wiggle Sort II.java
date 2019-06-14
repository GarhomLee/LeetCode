https://leetcode.com/problems/wiggle-sort-ii/

// 解法一：Sort
//        直接Sort，然后维护两个指针：left指向数组中间元素（如果偶数个元素则指向中间偏左的元素），right指向数组最右元素。
//        然后，根据位置奇偶，先放较小元素left，再放较大元素right，保证nums[0] < nums[1] > nums[2] < nums[3]...
// 时间复杂度：O(n log g)
// 空间复杂度：O(n)
// 犯错点：1.题目要求nums[0] < nums[1]，所以如果直接返回length < 3的话[2,1]的情况会出错
//        2.如果从0和length-1两端向中间放元素，那么如果中间两个元素相等就会出错

class Solution {
    public void wiggleSort(int[] nums) {
        //if (nums.length < 3) return;  // {Mistake 1: it is required that nums[0] < nums[1], and [2,1] will not pass}
                                        // {Correction 1: delete it}
        
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        Arrays.sort(copy);
        //int left = 0, right = nums.length - 1;  // {Mistake 2: test case [1,2,2,3]}
        int left = (nums.length + 1) / 2 - 1;  // {Correction 2: make sure that left is pointing to the median (or left median if even number of elements)}
        int right = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            //nums[i] = i % 2 == 0 ? copy[left++] : copy[right--];  // {Mistake 2}
            nums[i] = i % 2 == 0 ? copy[left--] : copy[right--];  // {Correction 2}
        }
    }
}


解法二：Quick Select (find Kth Largest) + Sort Colors
        https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
时间复杂度：O(n)
空间复杂度：O(1)