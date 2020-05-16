https://leetcode.com/problems/destination-city/

idea: HashMap
    Record outdegrees, and find the one with outdegree 0.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> outdegree = new HashMap<>();
        for (List<String> edge : paths) {
            String from = edge.get(0), to = edge.get(1);
            outdegree.put(from, outdegree.getOrDefault(from, 0) + 1);
            outdegree.putIfAbsent(to, 0);
        }
        
        for (String key : outdegree.keySet()) {
            if (outdegree.get(key) == 0) {
                return key;
            }
        }
        
        return "";
    }
}