https://leetcode.com/problems/adding-two-negabinary-numbers/

思路：Math + Bit Manipulation。参考：https://leetcode.com/problems/adding-two-negabinary-numbers/discuss/303751/C%2B%2BPython-Convert-from-Base-2-Addition

时间复杂度：O(max(len1, len2))
空间复杂度：O(max(len1, len2))

class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        Stack<Integer> stack = new Stack<>();
        int carry = 0;
        int i = arr1.length - 1, j = arr2.length - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            carry += i >= 0 ? arr1[i--] : 0;
            carry += j >= 0 ? arr2[j--] : 0;
            stack.push(carry & 1);
            carry = -(carry >> 1);
        }
        
        while (stack.size() > 1 && stack.peek() == 0) {
            stack.pop();
        }
        
        int[] res = new int[stack.size()];
        for (int k = 0; k < res.length; k++) {
            res[k] = stack.pop();
        }
        
        return res;
    }
}