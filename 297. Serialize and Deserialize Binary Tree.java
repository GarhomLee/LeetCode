https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

// 思路：Recursion
//      在serialization时，采用preorder traversal，利用'#'表示null，利用','分隔前后两个node。将临时结果
//      用StringBuilder存储效率更高。结果返回String。
//      在deserialization时，过程相反。将input以','分割后存在Queue中（或者array+pointer），利用preorder traversal
//      的DFS写法构建树，同时不断将用过的String从Queue中移除。

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /* preorder traversal */
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('#').append(',');
            return;
        }
        sb.append(root.val).append(',');
        serialize(root.left, sb);
        serialize(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList();
        queue.addAll(Arrays.asList(data.split(",")));  // there will be no element on the right of the last ','
        TreeNode root = deserialize(queue);
        return root;
    }
    
    /* use a Queue with result of preorder traversal to construct a tree */
    private TreeNode deserialize(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
    }
}


// 优化：用Binary的方式来记录节点的值 http://zxi.mytechroad.com/blog/tree/leetcode-297-serialize-and-deserialize-binary-tree/