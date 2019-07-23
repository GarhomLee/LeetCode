https://leetcode.com/problems/convert-a-number-to-hexadecimal/

// 思路：Bit Manipulation + 进制转换
//         对于num的二进制表示，每4位即代表1个十六进制数位。因此，num & 15的结果就是得到当前num最低位的十六进制数位。
//         从低到高得到各个十六进制数位（'0'到'f'），从后往前加入StringBuilder（或直接append最后reverse）。
// 犯错点：1.边界条件错误：当num == 0，while循环不会执行，sb最后什么都不返回。即使初始化时带有空字符串""，不进入
//             while循环的话最后依然返回空字符串""，而不是"0"。因此，要对num == 0的情况特殊讨论。

class Solution {
    public String toHex(int num) {
        // {Mistake 1}
        /* corner case */
        if (num == 0) return "0";  // {Correction 1}
        
        char[] mapping = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(mapping[num & 15]);
            num = num >>> 4;
        }
        return sb.reverse().toString();
    }
}