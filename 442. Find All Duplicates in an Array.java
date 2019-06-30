https://leetcode.com/problems/find-all-duplicates-in-an-array/

// 思路：HashMap（或者长度为n + 1的count数组）
//      利用辅助的HashMap（或count数组）存储nums数组中数字出现的次数，如果有出现2次，加入res列表。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList();
        int[] count = new int[nums.length + 1];
        
        for (int n: nums) {
            count[n]++;
            if (count[n] == 2) res.add(n);
        }
        
        return res;
    }
}


// follow-up：要求时间复杂度O(n)，空间复杂度O(1)
// 思路：因为题目规定nums数组的数字限定在[1:n]，所以可以套用找missing numbers的模版，将所有数组放在和index对应的位置。
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.要进行两次遍历，第一次遍历时不能直接判断i是否等于nums[i] - 1，因为此时还有些数没有被处理，可能造成重复结果。
//          因此，要遍历处理完所有元素后，在第二次遍历时判断i是否等于nums[i] - 1。

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList();
        
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {  // put nums[i] in proper position
                swap(nums, i, nums[i] - 1);
            }
            //if (i != nums[i] - 1) res.add(nums[i]);  // {Mistake 1: for now not all numbers are in proper position}
        }
        
        for (int i = 0; i < nums.length; i++) {  // {Correction 1}
            if (i != nums[i] - 1) res.add(nums[i]);
        }
        
        return res;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}