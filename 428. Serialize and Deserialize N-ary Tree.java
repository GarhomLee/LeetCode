https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

思路：因为不是binary tree，无法知道children准确个数，所以可以给每个节点都额外增加一个子节点'#'，
        也就是说叶子节点也会有一个子节点'#'，其他节点在当前子节点基础上多出来一个子节点'#'。
        当deserialize时，如果index指向的String为'#'，说明所有子节点都deserialize完毕，
        将index++后返回当前节点node。
时间复杂度：O(n)
空间复杂度：O(n)
犯错点：1.边界条件错误：当root==null时，serialize的结果为空字符串，进行String.split(",")时
            【String[]长度为1】。而当root!=null，String[]长度至少为2。

class Codec {
    private void dfs1(Node node, StringBuilder sb) {
        if (node == null) return;
        
        sb.append(node.val).append(',');
        
        // all valid children
        for (Node child : node.children) {
            dfs1(child, sb);
        }
        // last child to indicate the end of children list
        sb.append("#,");
    }
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        dfs1(root, sb);
        return sb.toString();
    }
    
    int index = 0;
    
    private Node dfs2(String[] split) {
        Node node = new Node(Integer.parseInt(split[index++]), new ArrayList<Node>());
        while (!split[index].equals("#")) {
            node.children.add(dfs2(split));
        }
        index++;
        
        return node;
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        
        String[] split = data.split(",");
        return dfs2(split);
    }
}