https://leetcode.com/problems/flipping-an-image/

// 思路：Bit Manipulation
// 时间复杂度：O(m*n)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：如果一维数组个数为奇数，那么最中间的那个元素只需要翻转一次。因此，需要判断left是否等于right。

class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int[] arr: A) {
            int left = 0, right = arr.length - 1;
            while (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                
                arr[left] ^= 1;
                if (left != right) arr[right] ^= 1;
                
                left++;
                right--;
            }
        }
        
        return A;
    }
}