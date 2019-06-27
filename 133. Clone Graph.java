https://leetcode.com/problems/clone-graph/

// 思路：Graph + DFS + HashMap
//      用helper method，利用DFS生成和从node开始的整个图的deep clone。
//      终止条件：Map中已经存在了当前node，说明已经走过这个节点，且对应的copy也存在，直接返回copy。
//      递归：生成当前节点对应的copy，且放进Map对应起来后，对于当前节点的所有相邻节点，调用同样的helper method生成它们的copy，
//           放进当前节点的copy的neighbors列表里。
// 犯错点：1.思路错误：不能只用Set来记录走过的node，因为这样没有办法得到和走过的node相对应的copy
//        2.新生成一个copy后，要放进Map里和原本的node对应起来，后面遇到走过的node时可以直接拿出来，否则会造成死循环

class Solution {
    Map<Node, Node> map = new HashMap();
    
    public Node cloneGraph(Node node) {
        return clone(node);
    }
    
    private Node clone(Node node) {
        if (map.containsKey(node)) return map.get(node);
        
        Node copy = new Node(node.val, new ArrayList<Node>());
        // {Mistake 2}
        map.put(node, copy);  // {Correction 2: map this node to its copy so that it can be used later}
        for (Node next: node.neighbors) {
            copy.neighbors.add(clone(next));
        }
        return copy;
    }
}