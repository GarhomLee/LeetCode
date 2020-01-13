https://leetcode.com/problems/sort-an-array/

思路：Bubble Sort, Selection Sort, Insertion Sort, Merge Sort, Quick Sort, Heap Sort

解法一：Built-in Arrays.sort() (Merge Sort?)
时间复杂度：O(n log n)
空间复杂度：O(n)

class Solution {
    public List<Integer> sortArray(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            res.add(num);
        }
        return res;
    }
}


解法二：Quick Sort
时间复杂度：O(n log n)
空间复杂度：O(log n)

class Solution {
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void quicksort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        
        int left = low + 1, right = high;
        while (left <= right) {
            if (nums[left] > nums[low]) {
                swap(nums, left, right--);
            } else {
                left++;
            }
        }
        swap(nums, low, right);
        
        quicksort(nums, low, right - 1);
        quicksort(nums, right + 1, high);
    }
    
    public List<Integer> sortArray(int[] nums) {
        List<Integer> res = new ArrayList<>();
        quicksort(nums, 0, nums.length - 1);
        for (int num : nums) {
            res.add(num);
        }
        return res;
    }
}