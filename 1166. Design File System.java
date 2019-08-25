https://leetcode.com/contest/biweekly-contest-7/problems/design-file-system/

// 思路：HashMap。类似Trie的结构，将next数组换成HashMap，key为字符串，value为对应的下一个节点Node。
//         step0: 自定义class Node实现类似Trie的结构，存放以某个字符串为结尾的file作为int val，同时存放当该字符串为
//                 中间路径时所有可能的子路径作为Map<String, Node> next。
//         step1: 实现boolean create(String path, int value)，将value对应到有效路径path的最后一个file。
//                 实际上，就是建立Trie的过程。
//                 维护Node curr，从root开始，不断寻找将path按"/"分割后的【倒数第二个file】，然后将【最后一个file】
//                 和对应的Node加入HashMap中。
//                 如果有某个中间file找不到或空字符串""，返回false。
//         step2: 实现int get(String path)，查找和有效路径path的最后一个file对应的值。
//                 实际上，就是Trie搜索的过程。
//                 维护Node curr，从root开始不断寻找将path按"/"分割后的【最后一个file】，返回对应Node中储存的val。
// 时间复杂度：create(): O(n); get(): O(n)
// 空间复杂度：O(n)
// 犯错点：1.Java内置函数使用不熟练：用String.split()按"/"分割时，由于"/"位于s[0]，因此分割后dirs[0]会是空字符串""，
//             所以循环要从dirs[1]开始。

class FileSystem {
    Node root;
    
    public FileSystem() {
        root = new Node(-1);
    }
    
    public boolean create(String path, int value) {
        String[] dirs = path.split("/");
        int len = dirs.length;
        Node curr = root;
        //for (int i = 0; i < len - 1; i++)  // {Mistake 1}
        for (int i = 1; i < len - 1; i++) {
            if (dirs[i].length() == 0 || !curr.next.containsKey(dirs[i])) {
                return false;
            }
            curr = curr.next.get(dirs[i]);
        }
        curr.next.put(dirs[len - 1], new Node(value));
        
        return true;
    }
    
    public int get(String path) {
        String[] dirs = path.split("/");
        int len = dirs.length;
        Node curr = root;
        for (int i = 1; i < len; i++) {
            if (!curr.next.containsKey(dirs[i])){
                return -1;
            }
            curr = curr.next.get(dirs[i]);
        }
        return curr.val;
    }
    
    class Node {
        int val;
        Map<String, Node> next;
        
        public Node(int v) {
            val = v;
            next = new HashMap<>();
        }
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.create(path,value);
 * int param_2 = obj.get(path);
 */