https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

思路1：DFS。因为不是binary tree，无法知道children准确个数，所以可以给每个节点都额外增加一个子节点'#'，
        也就是说叶子节点也会有一个子节点'#'，其他节点在当前子节点基础上多出来一个子节点'#'。
        当deserialize时，如果index指向的String为'#'，说明所有子节点都deserialize完毕，
        将index++后返回当前节点node。
时间复杂度：O(n)
空间复杂度：O(n)
犯错点：1.边界条件错误：当root==null时，serialize的结果为空字符串，进行String.split(",")时
            【String[]长度为1】。而当root!=null，String[]长度至少为2。

class Codec {
    StringBuilder sb = new StringBuilder();
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        dfs(root);
        return sb.toString();
    }
    
    private void dfs(Node node) {
        if (node == null) return;
        
        sb.append(node.val).append(',');
        
        for (Node next: node.children) {
            dfs(next);
        }
        sb.append('#').append(',');
    }
    
    int idx = 0;
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        
        return deserializeHelper(data.split(","));
    }
    
    private Node deserializeHelper(String[] split) {
        if (idx >= split.length || split[idx].equals("#")) {
            idx++;
            return null;
        }
        
        int val = Integer.parseInt(split[idx++]);
        Node curr = new Node(val, new ArrayList<>());
        
        Node next = deserializeHelper(split);
        while (next != null) {
            curr.children.add(next);
            next = deserializeHelper(split);
        }
        
        return curr;
    }
}


idea2: DFS, using Queue with children size recorded. See: https://www.youtube.com/watch?v=RmZNGMQvsig

class Codec {
    StringBuilder sb = new StringBuilder();
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        serializeHelper(root);
        return sb.toString();
    }
    
    private void serializeHelper(Node node) {
        if (node == null) return;
        
        sb.append(node.val).append(',').append(node.children.size()).append(',');
        for (Node child: node.children) {
            serializeHelper(child);
        }
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }
    
    private Node deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) return null;
        
        int val = Integer.parseInt(queue.poll()), size = Integer.parseInt(queue.poll());
        Node node = new Node(val, new ArrayList<>());
        while (size-- > 0) {
            Node child = deserializeHelper(queue);
            node.children.add(child);
        }
        
        return node;
    }
}