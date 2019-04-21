https://leetcode.com/problems/word-pattern/

// 题目要求str和pattern是bijection，一一对应，意味着pattern = "abcd" str = "dog cat cat dog"也应该返回false。
// 处理方法是用Set。
// 1）【corner cases】：当pattern长度和strArray长度不同，可以立刻返回false
// 2）维护一个String数组match，表示pattern里元素对应的String
// 3）维护一个Set，表示已经出现过的String，检测类似pattern = "abcd" str = "dog cat cat dog"的情况
// 4）如果pattern里的元素第一次出现，对应的match的value会是null，那么判断对应的String是否在set中。如果在set中，意味着已经对应
//     过之前的某个元素了，直接返回false；否则，加入match数组和set
// 5）如果match的value不为null，判断value和当前的String是否吻合
// 6）没有提前返回false的情况，跳出循环，返回true

class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strArray = str.split(" ");
        if (pattern.length() != strArray.length) return false;
        Set<String> set = new HashSet<>();
        String[] match = new String[128];
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (match[c] == null) {
                if (!set.contains(strArray[i])) {
                    match[c] = strArray[i];
                    set.add(strArray[i]);
                } else return false;
            } else if (!match[c].equals(strArray[i])) {
                return false;
            }
        }
        return true;
    }
}