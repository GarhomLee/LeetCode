https://leetcode.com/problems/alien-dictionary/

// 解法一：Topological Sort (DFS)。关键点在于如何构建有向图。
//         step1: 构建节点为26个字母的有向图graph，用Map<Character, Set<Character>>表示，key为所有words中出现过的字符(from)，value
//             为比该字符小的下一个字符(to)。
//             首先，遍历所有word的所有字符，加入对应的HashSet。
//             然后，遍历所有words，将words[i-1]和words[i]的逐个字符比较，当出现不同的字符时加入graph，然后直接跳出循环
//             搜索下一个words[i-1]和words[i]。
//         step2: DFS进行Topological Sort。
//             维护visited数组（和BFS不同），0表示未遍历的节点，1表示正在遍历中的节点，2表示遍历过的节点。
//             递归函数定义：boolean dfs(char curr, Map<Character, Set<Character>> graph, int[] visited)，
//                 表示搜索从当前字符（节点）curr开始是否会成环，如果成环，返回true，否则返回false。
//             终止条件：如果visited[curr - 'a'] != 0，那么如果为1，表示遍历回当前路径的某个节点，无效成环，因此返回true。
//                 否则为2，没有无效成环，返回false。
//             递归过程：将当前visited[curr - 'a']标记为1，然后调用递归函数遍历curr邻接的下一个节点next。
//                 如果某一个next返回true，那么当前路径成环，返回true。
//                 否则，遍历完所有next，当前visited[curr - 'a']标记为2表示遍历完成，同时将curr加入StringBuilder，返回false。
//         step3: 如果某一个节点检测出成环，返回空字符串""。
//             否则，由于DFS实现的Topological Sort会将最底端的节点先放入StringBuilder，所以要先reverse，再返回。
// 时间复杂度：O(V+E)
// 空间复杂度：O(V+E)

class Solution {
    StringBuilder sb = new StringBuilder();
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        /* initialize the graph */
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word: words) {
            for (char c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        
        /* build the graph */
        build(words, graph);
        
        /* DFS */
        int[] visited = new int[26];
        for (char c: graph.keySet()) {
            if (dfs(c, graph, visited)) {
                return "";
            }
        }
        
        return sb.reverse().toString();
    }
    
    private void build(String[] words, Map<Character, Set<Character>> graph) {
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1], w2 = words[i];
            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
            }
        }
    }
    
    private boolean dfs(char curr, Map<Character, Set<Character>> graph, int[] visited) {
        if (visited[curr - 'a'] != 0) {
            return visited[curr - 'a'] == 1;
        }
        
        visited[curr - 'a'] = 1;
        for (char next: graph.get(curr)) {
            if (dfs(next, graph, visited)) {
                return true;
            }
        }
        
        visited[curr - 'a'] = 2;
        sb.append(curr);
        return false;
    }
}


// 解法二：Topological Sort (BFS)。视频讲解：https://www.youtube.com/watch?v=RIrTuf4DfPE
//         step1: 构建节点为26个字母的有向图graph，和解法一类似。
//             和DFS不同点在于，要维护一个indegree数组，表示每个节点的入度。
//             当从节点c1到节点c2的边第一次出现，那么更新indegree[c2-'a']++，同时将c2放入c1对应的HashSet中。
//         step2: BFS进行Topological Sort。
//             遍历graph的keySet()，将所有【入度为0的节点】放入Queue，作为seed。
//             不断从Queue中取出节点curr，首先加入StringBuilder，然后遍历所有邻接节点next，将next的入度-1.
//             如果next的入度为0，那么需要加入Queue中，准备被搜索。
//         step3: 利用BFS的【无效成环检测】在于最后StringBuilder中字符个数是否为所有出现的字符个数graph.size()。
//             只有sb.length() == graph.size()的结果才是合法的Topological Sort结果。
// 时间复杂度：O(V+E)
// 空间复杂度：O(V+E)
// 犯错点：1.细节错误：HashMap中从当前字符curr出发的相邻节点next在HashSet中，而不是HashMap的keySet()中。
//         2.细节错误：对于Topological Sort的BFS做法，只有在下一层节点的indegree为0的时候才能加入Queue，否则不予考虑。

class Solution {
    int[] indegree = new int[26];
    
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String word: words) {
            for (char c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        
        // build graph
        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1], w2 = words[i];
            int len = Math.min(w1.length(), w2.length());
            boolean diffFound = false;
            for (int j = 0; j < len && !diffFound; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    diffFound = true;
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree[c2 - 'a']++;
                    }
                }
            }
            
            // input can be invalid
            if (!diffFound && w1.length() > w2.length()) {
                return "";
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char c: graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            sb.append(curr);
            
            //for (char next: graph.keySet(curr))  // {Mistake 1}
            for (char next: graph.get(curr)) {  // {Correction 1}
                indegree[next - 'a']--;
                //if (indegree[next - 'a'] > 0)  // {Mistake 2}
                if (indegree[next - 'a'] == 0) {  // {Correction 2}
                    queue.offer(next);
                }
            }
        }
        
        return sb.length() == graph.size() ? sb.toString() : "";
    }    
}