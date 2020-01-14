

思路：Brute Force
        按index从大到小，找到当前范围[0:i]的最大的数的位置j，先翻转到位置0，然后翻转到位置i。
        然后缩小范围至[0:i-1]。
时间复杂度：O(N^2)
空间复杂度：O(2N)=O(N)

class Solution {
    private void swap(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            int j = i;
            while (j >= 0 && A[j] != i + 1) {
                j--;
            }
            if (j == i) continue;
            
            res.add(j + 1);
            swap(A, 0, j);
            res.add(i + 1);
            swap(A, 0, i);
        }
        
        return res;
    }
}