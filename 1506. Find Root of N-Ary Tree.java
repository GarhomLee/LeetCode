https://leetcode.com/problems/find-root-of-n-ary-tree/

solution1: HashMap
time complexity: O(n)
space complexity: O(n)

class Solution {
    public Node findRoot(List<Node> tree) {
        if (tree.isEmpty()) return null;
        
        Map<Integer, Node> map = new HashMap<>();    // child val -> parent val
        for (Node node : tree) {
            for (Node child : node.children) {
                map.put(child.val, node);
            }
        }
        
        Node curr = tree.get(0);
        while (map.containsKey(curr.val)) {
            curr = map.get(curr.val);
        }
        return curr;
    }
}


follow-up solution2: Bit Manipulation (similar to 136. Single Number)
    -Since the nodes with parent will be iterated twice, using XOR will only leave out the root node val
     which is visited only once.
time complexity: O(n)
space complexity: O(1)

class Solution {
    public Node findRoot(List<Node> tree) {
        int val = 0;
        for (Node node : tree) {
            val ^= node.val;
            for (Node child : node.children) {
                val ^= child.val;
            }
        }
        
        for (Node node : tree) {
            if (node.val == val) {
                return node;
            }
        }
        
        return null;
    }
}