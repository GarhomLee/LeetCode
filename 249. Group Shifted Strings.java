https://leetcode.com/problems/group-shifted-strings/

// 思路：HashMap
//         step1:遍历strings字符串数组的每个字符串str，将所有str“标准化”，即按第一个字符str[0]到
//             'a'的距离offset，平移整个str，变成以'a'开头的字符串，然后将平移后的'a'开头字符串相同
//             的这些字符串归为一组。HashMap中，key为以'a'开头的字符串，value为同为一组的字符串。
//         step2:遍历HashMap，对同一组的所有字符串排序，加入res列表中。
// 时间复杂度：O(s * l), s=strings.length, l=strings[i].length
// 空间复杂度：O(s * l), s=strings.length, l=strings[i].length

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        /* normalize strings[i] to a String with same length starting from 'a' */
        for (String str: strings) {
            StringBuilder sb = new StringBuilder();
            int offset = str.charAt(0) - 'a';
            for (char c: str.toCharArray()) {
                sb.append((char) ((c - offset + 26) % 26));
            }
            
            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        /* sort strings in the same group and add into res List */
        List<List<String>> res = new ArrayList<>();
        for (String key: map.keySet()) {
            Collections.sort(map.get(key));
            res.add(new ArrayList<>(map.get(key)));
        }
        return res;
    }
}