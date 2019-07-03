https://leetcode.com/problems/keyboard-row/

// 思路：HashMap
//      HashMap的key为每一个字母（小写），value为对应的组别，在键盘同一行的所有字母都在同一组别，共有3组。
//      先构建HashMap，然后遍历words字符串数组。对于里面的每一个字符串，利用HashMap判断是否所有字符都来自于同一
//      个组，如果是，加入结果list，否则跳过不管。
// 犯错点：1.细节错误：字符串里可以同时有大小写字母，需要统一转换成小写字母

class Solution {
    public String[] findWords(String[] words) {
        /* assign each letter to a certain group according to its keyboard row */
        String[] groups = new String[]{"qwertyuiop","asdfghjkl","zxcvbnm"};
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < groups.length; i++) {
            for (char c: groups[i].toCharArray()) {
                map.put(c, i);
            }
        }
        /* check if all chars in a word come from the same group (keyboard row) */
        List<String> list = new ArrayList();
        for (String word: words) {
            if (word.length() == 0) continue;
            // {Mistake 1: word might contain both upper and lower cases}
            String temp = word.toLowerCase();  // {Correction 1}
            int index = map.get(temp.charAt(0));
            for (char c: temp.toCharArray()) {
                if (map.get(c) != index) {  // one char is not in the same group as others
                    index = -1;
                    break;
                }
            }
            if (index != -1) {  // it is not from breaking out of loop, which means all chars are in the same group
                list.add(word);
            }
        }
        
        /*String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;*/
        return list.toArray(new String[0]);
    }
}