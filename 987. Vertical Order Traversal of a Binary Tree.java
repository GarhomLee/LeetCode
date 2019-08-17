https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

// 思路：DFS + Customized Sort
//         step0: 构建class Location，记录TreeNode的val以及对应的x和y坐标信息。
//                 关键：自己实现compareTo()，按x从小到大、y从小到大、val从小到大排序
//         step1: DFS遍历整个树，将TreeNode的val以及对应的x和y坐标信息转成Location后放入List中
//         step2: 将所有List中的Location按自定义的compareTo()排序
//         step3: 将排好序的Location按x坐标分组，分别加入res中。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.空指针错误：pre初始化为null，如果要更新pre.x为curr.x，需要先判断pre是否为null，如果为null则需要初始化。
//         2.Java语言知识错误：要重写class里的compareTo()，【需要implements Comparable，而不是Comparator】。

class Solution {
    List<Location> locations = new ArrayList<>();
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        Collections.sort(locations);
        
        Location pre = null;
        List<List<Integer>> res = new ArrayList<>();
        for (Location curr: locations) {
            if (pre == null || curr.x != pre.x) {
                res.add(new ArrayList<>());
            }
            res.get(res.size() - 1).add(curr.val);
            // {Mistake 1}
            if (pre == null) {
                pre = new Location(0, 0, 0);
            }  // {Correction 1}
            pre.x = curr.x;
        }
        
        return res;
    }
    
    private void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        
        locations.add(new Location(x, y, root.val));
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
    
    //class Location  // {Mistake 2}
    class Location implements Comparable<Location> {  // {Correction 2}
        int x;
        int y;
        int val;
        
        public Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo(Location other) {
            if (this.x != other.x) {
                return this.x - other.x;
            } else if (this.y != other.y) {
                return this.y - other.y;
            } else {
                return this.val - other.val;
            }
        }
    }
}