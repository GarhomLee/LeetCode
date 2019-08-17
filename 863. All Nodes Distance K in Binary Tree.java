https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

// 解法一：Graph (HashMap + ArrayList) + BFS
//         step1: 遍历整个树，构建无向图。
//                 HashMap中，key为当前节点，value为邻接节点的List，邻接节点同时包括子节点和父节点。
//         step2: 利用Queue【从target开始】进行BFS找到距离为K的节点，同时利用HashSet去重。
//         step3: 将走了K步后Queue中剩下的节点的val加入List res，最后返回res。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.初始条件错误：BFS的初始值应该为target，而不是root

class Solution {
    Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        /* graph construction */
        build(root);
        /* BFS initialization */
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        if (root != null) {
            //queue.offer(target);
            //set.add(target);  // {Mistake 1}
            queue.offer(target);
            set.add(target);  // {Correction 1}
        }
        /* BFS */
        while (!queue.isEmpty() && K-- > 0) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curr = queue.poll();
                for (TreeNode next: graph.get(curr)) {
                    if (set.contains(next)) continue;
                    
                    queue.offer(next);
                    set.add(next);
                }
            }
            
        }
        /* put all results in List res */
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll().val);
        }
        
        return res;
    }
    /** build an undirected graph */
    private void build(TreeNode root) {
        if (root == null) {
            return;
        }
        
        graph.putIfAbsent(root, new ArrayList<>());
        if (root.left != null) {
            graph.putIfAbsent(root.left, new ArrayList<>());
            graph.get(root.left).add(root);
            graph.get(root).add(root.left);
        }
        if (root.right != null) {
            graph.putIfAbsent(root.right, new ArrayList<>());
            graph.get(root.right).add(root);
            graph.get(root).add(root.right);
        }
        
        build(root.left);
        build(root.right);
    }
}


解法二：Recurion。视频讲解：https://www.youtube.com/watch?v=o1siL8eKCos
时间复杂度：O(n)
空间复杂度：O(n)