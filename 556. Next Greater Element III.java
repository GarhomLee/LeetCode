https://leetcode.com/problems/next-greater-element-iii/

// 思路：类似31. Next Permutation。
//      首先，将integer转换成char数组，从右往左找到第一个“下降”（即i比i+1小）的字符，位置记为left。如果从右往左一直递增，
//      就返回-1.
//      然后，从右往左找到第一个恰好比digits[left]大的字符，位置记为right。
//      调换digits[left]和digits[right]。
//      然后，翻转digits[left+1:length)。
//      最后，将结果重新转换成integer。如果超过Integer.MAX_VALUE范围，返回-1.
// 犯错点：1.数值范围错误：如1999999999，它的next greater element超过Integer.MAX_VALUE范围，
//         因此要用long在存储，在返回结果前要判断结果范围，只有符合范围的才强制转为int返回。

class Solution {
    public int nextGreaterElement(int n) {
        /* corner case */
        if (n < 12) return -1;
        /* transform an integer to char array */
        char[] digits = String.valueOf(n).toCharArray();
        /* find the first decreasing number from right towards left */
        int left = digits.length - 2;
        while (left >= 0 && digits[left] >= digits[left + 1]) {
            left--;
        }
        if (left == -1) return -1;  // this is an increasing array from right to left
        /* find the first number greater than digits[left+1] in digits[left+1:length) */
        int right = digits.length - 1;
        while (digits[right] <= digits[left]) {
            right--;
        }
        swap(digits, left, right);
        /* reverse digits[left+1:length) */
        left = left + 1;
        right = digits.length - 1;
        while (left < right) {
            swap(digits, left++, right--);
        }
        /* convert the result to integer*/
        //int res = 0;  // {Mistake 1: test case: 1999999999, its next greater element will be greater than Integer.MAX_VALUE}
        long res = 0;  // {Correction 1}
        for (char c: digits) {
            res = res * 10 + (c - '0');
            if (res > Integer.MAX_VALUE) return -1;  // {Correction 1}
        }
        
        return (int) res;  // {Correction 1}
    }
    
    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}