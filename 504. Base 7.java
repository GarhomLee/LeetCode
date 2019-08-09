https://leetcode.com/problems/base-7/

// 思路：进制转换
//         模版背下来！
// 时间复杂度：O(log n)
// 空间复杂度：O(log n)
// 犯错点：1.公式错误：先得到的是位数低的数，所以最后要翻转。
//         2.边界条件错误：num == 0时，循环不被执行，最后返回空字符串""，造成错误。因此num == 0单独讨论。

class Solution {
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        
        int sign = num < 0 ? -1 : 1;
        StringBuilder sb = new StringBuilder();
        num = (int) Math.abs(num);
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        
        if (sign < 0) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}