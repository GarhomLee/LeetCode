https://leetcode.com/problems/rearrange-words-in-a-sentence/

idea: TreeMap
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public String arrangeWords(String text) {
        TreeMap<Integer, List<String>> treeMap = new TreeMap<>();
        String[] split = text.split("\\s+");
        for (String s : split) {
            int n = s.length();
            treeMap.putIfAbsent(n, new ArrayList<>());
            treeMap.get(n).add(s.toLowerCase());
        }
        
        List<String> list = new ArrayList<>();
        for (int key : treeMap.keySet()) {
            list.addAll(treeMap.get(key));
        }
        
        StringBuilder sb = new StringBuilder(String.join(" ", list));
        char c = sb.charAt(0);
        sb.setCharAt(0, Character.toUpperCase(c));
        
        return sb.toString();
    }
}