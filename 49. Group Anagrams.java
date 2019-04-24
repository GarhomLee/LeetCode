https://leetcode.com/problems/group-anagrams/

// 1）维护一个Map，key为每个string字符排序后作为anagram group representative，value为属于这个group的所有string
// 2）遍历String数组，先转换成char array，排序，再转换成String，判断是否在Map中

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if (!map.containsKey(sorted)) map.put(sorted, new ArrayList<String>());
            map.get(sorted).add(s);
        }
        
        for(List<String> value: map.values()) resultList.add(value);
        return resultList;
    }
}