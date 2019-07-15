https://leetcode.com/problems/reconstruct-original-digits-from-english/

// 思路：找规律。
//         可以发现一个巧合，对于0-9中的偶数的英文单词，都有唯一对应的字母，如只有0中有'z'，2中有'w'等。
//         对于奇数，虽然没有唯一对应的字母，但是可以选择符合以下两个条件的字母：
//         1）在其他单词中出现得尽可能少
//         2）在每个单词中只出现一次，如"nine"的'n'或"three"的'e'不是合适的选择
//         将字母对应的数字利用count数组统计，然后将重复的部分减去，然后按数字从小到大加入StringBuilder。
// 犯错点：1.思路错误：由于'n'在"nine"中出现不止一次，所以不能简单的将重叠的其他数字出现次数减去。
//             因此，不如挑选一个只在"nine"出现一次，且在其他单词中出现尽量少的字母，如'i'。

class Solution {
    public String originalDigits(String s) {
        if (s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int[] count = new int[10];
        for (char c: s.toCharArray()) {
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'u') count[4]++;
            if (c == 'x') count[6]++;
            if (c == 'g') count[8]++;
            if (c == 'h') count[3]++;  // 3, 8
            if (c == 'f') count[5]++;  // 5, 4
            if (c == 's') count[7]++;  // 7, 6
            //if (c == 'n') count[9]++;  // {Mistake 1: there will be 2 ns in word "nine"}
            if (c == 'i') count[9]++;  // 9, 5, 6, 8
                                        // {Correction 1: use 'i' to represent 9}
            if (c == 'o') count[1]++;  // 1, 0, 2, 4
        }
        
        count[3] -= count[8];
        count[5] -= count[4];
        count[7] -= count[6];
        //count[9] -= count[1] + count[7] // {Mistake 1}
        count[9] -= count[5] + count[6] + count[8];  // {Correction 1}
        count[1] -= count[0] + count[2] + count[4];
        
        for (int i = 0; i < 10; i++) {
            while (count[i]-- > 0) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
