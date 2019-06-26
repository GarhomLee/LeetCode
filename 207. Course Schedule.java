https://leetcode.com/problems/course-schedule/

// 思路：Graph + DFS
//      构建graph，用List of List实现。
//      对于每一节课（即每个节点），DFS搜索是否成环。如果成环，那么不能满足选课要求，返回false。
//      重点是维护一个state数组，记录每个节点的状态。0表示还没访问，1表示所处的路径正在被访问，2表示已经被访问过了。
//      对于每个当前节点，递归遍历与之相连的节点。
//      终止条件：1）state[curr] == 1，返回true，表示遇到了环
//               2）state[curr] == 2，返回false，表示遇到了走过的节点。因为这时还没返回true，所以这个走过的节点
//                 也不会形成环，所以返回false。
//      递归：遍历与当前节点相连的节点。只要有一个true，就返回true。如果都没有true，就可返回false。
// 注意：1.可以将List of List改为array of List或者array of Set
//      2.先将所有course都new一个List。如果不做这个预处理，那么graph如果外层用的是array，那么就会有null的情况，要多进行一次讨论。

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /* graph construction */
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<Integer>());  // preprocessing

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            graph.get(prerequisite).add(course);  // direction: from prerequisite to courses
        }
        /* maintain a array that records the visiting state: 0 is not visited, 1 is visiting, 2 is visited */
        int[] state = new int[numCourses];
        /* dfs */
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(graph, state, i)) return false;  // a cycle is found, therefore it is not possible to finish
        }
        return true;
    }
    
    /** helper method for finding cycle, return true if a cycle is found */
    private boolean isCyclic(List<List<Integer>> graph, int[] state, int curr) {
        if (state[curr] == 1) return true;  // cycle found
        if (state[curr] == 2) return false;  // already visited
        state[curr] = 1;
        for (int next: graph.get(curr)) {
            if (isCyclic(graph, state, next)) return true;
        }
        state[curr] = 2;
        return false;
    }
}