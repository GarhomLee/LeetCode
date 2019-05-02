https://leetcode.com/problems/multiply-strings/

// 考察数学的基础，还原成加法。图解：https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
// 因为最后返回的是String，遍历的过程也只运用了两个一位数相乘，所以不需要考虑过界的情况。

// 1）维护digits数组，表示两数乘积的每个位数，长度为这两个数的长度之和。可以用两个数的最大位数乘积作为证明。
// 2）从低位到高位遍历两个数，做双重循环。注意：一定是从低位到高位遍历，否则加和的carry不能得到及时处理
// 3）维护临时变量product表示两个数当前位数的乘积，注意：这个乘积一定小于等于81，因为是两个一位数相乘。
// 4）把这个乘积加到digits的相应位置，低位记为index2，高位记为index1。因为一定是个两位数，所以有index1 = index2 - 1的关系。
//     关键点：index2 = i + j + 1，可以由i == num1.length() - 1 && j = num2.length() - 1时推得。
// 5）维护临时变量sum表示product和【低位的digits[index2]】的和，根据sum来更新digits[index1]和digits[index2]；
// 6）把digits数组转换成String。注意：考虑leading 0s的情况，以及最后String是否长度为0的情况。

class Solution {
    public String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) return "0";
        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int index2 = i + j + 1, index1 = index2 - 1;
                int sum = digits[index2] + product;
                digits[index1] += sum / 10;
                digits[index2] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            if (digit == 0 && sb.length() == 0) continue;
            sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}