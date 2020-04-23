https://leetcode.com/problems/before-and-after-puzzle/

idea: HashMap + TreeSet
time complexity: O(n^2*l), n=phrases.length, l=average length of phrase
space complexity: O(n^2*l), n=phrases.length, l=average length of phrase

class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, List<Integer>> prefixMap = new HashMap<>();
        TreeSet<String> treeSet = new TreeSet<>();
        
        for (int i = 0; i < phrases.length; i++) {
            String[] split = phrases[i].split("\\s+");
            String prefix = split[0], suffix = split[split.length - 1];
            prefixMap.putIfAbsent(prefix, new ArrayList<>());
            prefixMap.get(prefix).add(i);
        }
        
        for (int i = 0; i < phrases.length; i++) {
            String[] split = phrases[i].split("\\s+");
            String prefix = split[0], suffix = split[split.length - 1];
            
            if (!prefixMap.containsKey(suffix)) continue;
            
            for (int j : prefixMap.get(suffix)) {
                if (i == j) continue;
                String s = phrases[j].substring(suffix.length());
                treeSet.add(phrases[i] + s);
            }
        }
        
        return new ArrayList<String>(treeSet);
    }
}