https://leetcode.com/problems/evaluate-division/

// 思路：Graph问题，除数和被除数可以形成directed graph（双向）。
//      构建class Graph，内部以Map of Maps实现。外层Map的key是被除数，value是和除数有关的Map；内层Map的key是除数，value是商。
//      class Graph实现以下功能：
//      1）addEdge(String firstStr, String secondStr, double value)，构建整个Graph
//      2）hasVertex(String s)，查看s是否出现在equations里。
//      3）getVertices(String s)，得到与s有关的、以s为被除数的所有除数的Set
//      4）getQuotient(String firstStr, String secondStr)得到以firstStr为被除数、secondStr为除数的商
//      构建好Graph后，对于每一个query，利用DFS来搜索是否存在答案，同时利用Set来记录走过的节点，防止走回头路。
//      终止条件：firstStr和secondStr为同一个String，自己除以自己，返回1.
//      递归：对于所有和当前firstStr连接的节点next，已知firstStr / next的结果，利用递归求所有next / secondStr的结果，记为temp。
//           如果temp > 0，说明next / secondStr的结果可求，所以firstStr / secondStr = firstStr / next * temp。
//           否则，next / secondStr的结果不可求，返回-1.

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];
        Graph graph = new Graph();

        /* graph construction */
        for (int i = 0; i < equations.size(); i++) {
            String firstStr = equations.get(i).get(0), secondStr = equations.get(i).get(1);
            double value = values[i];
            
            graph.addEdge(firstStr, secondStr, value);
            graph.addEdge(secondStr, firstStr, 1 / value);
        }
        
        /* use dfs to search the result of firstStr / secondStr */
        for (int i = 0; i < queries.size(); i++) {
            String firstStr = queries.get(i).get(0), secondStr = queries.get(i).get(1);
            if (!graph.hasVertex(firstStr) || !graph.hasVertex(secondStr)) {
                res[i] = -1;
            } else {
                res[i] = dfs(graph, firstStr, secondStr, new HashSet<String>());
            }
        }
        
        return res;
    }
    
    /** use dfs to search the result of firstStr / secondStr */
    private double dfs(Graph graph, String firstStr, String secondStr, Set<String> visited) {
        if (firstStr.equals(secondStr)) return 1;  // divide by itself
        
        visited.add(firstStr);  // mark firstStr as visited to avoid going back
        Set<String> vertices = graph.getVertices(firstStr);  // get all vertices related to firstStr
        for (String next: vertices) {
            if (!visited.contains(next)) {
                double temp = dfs(graph, next, secondStr, visited);  // split firstStr / secondStr as (firstStr / next) * temp, where temp = (next / secondStr)
                if (temp > 0) return graph.getQuotient(firstStr, next) * temp;  // result of next / secondStr exists in current graph
            }
        }
        
        return -1;  // result of next / secondStr doesn't exist in current graph
    }
    
    /** a graph specific to this particular question */
    class Graph {
        Map<String, Map<String, Double>> graph;
        Graph() {
            graph = new HashMap();
        }
        /** add the edge firstStr->secondStr with the quotient */
        public void addEdge(String firstStr, String secondStr, double value) {
            if (!graph.containsKey(firstStr)) {
                graph.put(firstStr, new HashMap<String, Double>());
            }
            graph.get(firstStr).put(secondStr, value);
        }
        /** determine if the graph has vertex s */
        public boolean hasVertex(String s) {
            return graph.containsKey(s);
        }
        /** get all vertices adjacent to vertex s */
        public Set<String> getVertices(String s) {
            return graph.get(s).keySet();
        }
        /** get the quotient of firstStr / secondStr */
        public double getQuotient(String firstStr, String secondStr) {
            return graph.get(firstStr).get(secondStr);
        }
    }
}