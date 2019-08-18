https://leetcode.com/problems/maximum-swap/

// 思路：Greedy
//         step1: 将num转成char数组，遍历并在index数组中记录每个数字最后出现的位置。
//         step2: 再次遍历char数组，对每个字符，从9开始到当前字符+1的数字中如果出现的位置在当前字符右边，那么
//                 直接将那个字符和当前字符交换即可，然后转成int后返回。
//         step3: 如果遍历完char数组，都没有交换，那么直接返回num本身。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int maximumSwap(int num) {
        /* record the last occurance of each digit in char[] arr */
        char[] arr = String.valueOf(num).toCharArray();
        int[] index = new int[10];
        for (int i = 0; i < arr.length; i++) {
            index[arr[i] - '0'] = i;
        }
        /* swap and return if there is a largest digit occurring on the right of current digit */
        for (int i = 0; i < arr.length; i++) {
            for (int digit = 9; digit > arr[i] - '0'; digit--) {
                if (index[digit] > i) {
                    char temp = arr[i];
                    arr[i] = arr[index[digit]];
                    arr[index[digit]] = temp;
                    return Integer.valueOf(new String(arr));
                }
            }
        }
        /* nothing to swap, return num itself */
        return num;
    }
}