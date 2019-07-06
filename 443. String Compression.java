https://leetcode.com/problems/string-compression/

// 思路：Two pointers + 1 counter
//      维护两个指针：left指向接下来可以被填充的位置；right指向第一个未被扫描的位置，同时right可以指向chars.length，表示遍历完成。
//      维护一个counter，表示相同字符的个数。
//      left初始化为0，right初始化为1。在right扫描时，会出现以下情况：
//      1）chars[right] != chars[right - 1]，表示遇到新的字符，那么需要对原来的字符进行压缩，放在left。
//         先更新chars[left]为chars[right - 1]，同时left++。然后判断重复次数count，如果count > 1，才需要将
//         对应的数字转成char array，利用left指针依次填充。
//         然后count初始化为1
//      2）right == chars.length，chars数组遍历完了，同样需要更新chars[left]，过程同1）
//      3）chars[right] == chars[right - 1]，只需要更新count++
//      最后返回left即可。

class Solution {
    public int compress(char[] chars) {
        /* corner case */
        if (chars.length == 0) return 0;
        
        int count = 1;
        int left = 0;
        for (int right = 1; right <= chars.length; right++) {
            if (right == chars.length || chars[right] != chars[right - 1]) {  // state change
                chars[left++] = chars[right - 1];  // update chars[left]
                if (count > 1) {  // repeated more than once, append the number
                    String num = String.valueOf(count);
                    for (char c: num.toCharArray()) {
                        chars[left++] = c;
                    }
                }
                count = 1;  // reset
            } else if (chars[right] == chars[right - 1]) {  // state not change
                count++;
            }
        }
        return left;
    }
}