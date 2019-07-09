https://leetcode.com/problems/serialize-and-deserialize-bst/

// 思路：和297. Serialize and Deserialize Binary Tree代码完全一样。

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        //System.out.println(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(',');
        serialize(root.left, sb);
        serialize(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> split = Arrays.asList(data.split(","));
        Queue<String> queue = new LinkedList(split);
        
        return deserialize(queue);
    }
    
    private TreeNode deserialize(Queue<String> queue) {
        String curr = queue.poll();
        if (curr.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(curr));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
    }
}