https://leetcode.com/problems/course-schedule-ii/

// 思路：Graph + DFS，在利用207. Course Schedule的检测环是否存在的基础上进行topological sort。
// 注意：如果用array of Set，且没有初始化每个Set，那么可能会遇到null的情况。遇到null表示到尽头了，可以加入res数组，
//      同时state要设为2。如果进行过初始化，那么这种情况不需要单独讨论。
// 犯错点：1.对于所有加入res的节点，它的state都要设为2，表示已经遍历过了。

class Solution {
    int[] res;
    int index = 0;  // use global variable to fill res array and to avoid turning a List into array
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /* graph construction */
        Set<Integer>[] graph = new Set[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0], prerequisite = prerequisites[i][1];
            if (graph[course] == null) graph[course] = new HashSet();
            graph[course].add(prerequisite);
        }
        
        int[] state = new int[numCourses];
        res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(isCyclic(graph, i, state)) return new int[0];
        }
        
        return res;
    }
    
    private boolean isCyclic(Set<Integer>[] graph, int vertex, int[] state) {
        if (state[vertex] == 1) return true;
        if (state[vertex] == 2) return false;
        
        state[vertex] = 1;
        if (graph[vertex] == null) {  // because graph[i] is not initialized with an empty Set, this corner case should be evaluated
            // {Mistake 1}
            state[vertex] = 2;  // {Correction 1: this is the very end of the graph, thus the state should be set as 2}
            res[index++] = vertex;
            return false;
        }
        for (Integer next: graph[vertex]) {
            if(isCyclic(graph, next, state)) return true;
        }
        state[vertex] = 2;
        res[index++] = vertex;
        return false;
    }
}