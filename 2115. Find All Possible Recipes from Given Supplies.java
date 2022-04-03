https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

idea: Topological Sort
time comp: O(V+E)
space comp: O(V)

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> recipesSet = new HashSet<>(Arrays.asList(recipes));
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        // graph initialization
        for (String s: recipes) {
            graph.putIfAbsent(s, new HashSet<>());
            indegree.put(s, 0);
        }
        for (List<String> list: ingredients) {
            for (String s: list) {
                graph.putIfAbsent(s, new HashSet<>());
                indegree.put(s, 0);                
            }
        }
        for (String s: supplies) {            
            graph.putIfAbsent(s, new HashSet<>());
            indegree.put(s, 0);
        }
        
        // graph construction
        for (int i = 0; i < recipes.length; i++) {
            for (String ingredient: ingredients.get(i)) {
                graph.get(ingredient).add(recipes[i]);
            }
            indegree.put(recipes[i], indegree.get(recipes[i]) + ingredients.get(i).size());
        }
        
        List<String> ret = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        for (String supply: supplies) {
            queue.offer(supply);
        }
        
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (recipesSet.contains(curr)) {
                ret.add(curr);
            }
            
            for (String next: graph.get(curr)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }        
        
        return ret;
    }
}