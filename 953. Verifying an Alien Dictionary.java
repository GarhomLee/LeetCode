https://leetcode.com/problems/verifying-an-alien-dictionary/

// 思路：常规遍历 + indexOf()
//         step0: 特殊情况words.length <= 1，一定符合条件，返回true
//         step1: 依次遍历words数组中的字符串，比较当前字符出串words[i]和前一个字符串words[i - 1]在新的order
//             条件下的大小关系。
//         step2: 同时遍历words[i - 1]和words[i]的每一个字符，利用indexOf()查找各字符在order中的位置，分别记为
//             index1和index2。它们的大小关系可能有3种情况：
//             1）index1 > index2，说明在新的order下不满足从小到大排列，返回false
//             2）index1 < index2，满足从小到大排列，不需要继续比较，break
//             3）index1 == index2，继续搜索下一位
//             如果跳出循环是因为搜索完words[i]且words[i - 1]比words[i]长，那么也不满足条件，返回false。
//         step3: 没有不满足条件的情况，说明符合要求，返回true。
// 时间复杂度：O(n * l * 26), n=words.length, l=words[i].length()
// 空间复杂度：O(1)

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        /* corner case */
        if (words.length <= 1) {
            return true;
        }
        
        for (int i = 1; i < words.length; i++) {
            int len1 = words[i - 1].length(), len2 = words[i].length();
            int j = 0;
            while (j < len1 && j < len2) {
                char c1 = words[i - 1].charAt(j), c2 = words[i].charAt(j);
                int index1 = order.indexOf(c1), index2 = order.indexOf(c2);
                if (index1 > index2) {
                    return false;
                } else if (index1 < index2) {
                    break;
                } else {
                    j++;
                }
                
                if (j == len2 && len1 > len2) {
                    return false;
                }
            }
            
        }
        
        return true;
    }
}