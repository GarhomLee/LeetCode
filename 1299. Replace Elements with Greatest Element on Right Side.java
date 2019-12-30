https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/

// 思路：从右向左遍历，记录当前最大值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int[] replaceElements(int[] arr) {
        int max = -1, len = arr.length;
        for (int i = len - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        
        return arr;
    }
}